package com.emilburzo.templater;

import com.emilburzo.templater.pojo.TemplateReqRPC;
import com.emilburzo.templater.pojo.TemplateRespRPC;
import com.emilburzo.templater.rpc.AppService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    @Override
    public TemplateRespRPC process(TemplateReqRPC rpc) {
        // input data
        // separate to one per line for processing
        String[] inputLines = rpc.inputData.split("\\n");

        // final result placeholder
        String result = "";

        for (String inputLine : inputLines) {
            // ignore blank lines
            if (inputLine.trim().isEmpty()) {
                continue;
            }

            // if there's a separator, split the line with it
            if (rpc.separator != null && !rpc.separator.trim().isEmpty()) {
                String[] args = inputLine.split(rpc.separator);

                String tmp = rpc.template;
                for (int i = 0; i < args.length; i++) {
                    String arg = args[i];

                    tmp = tmp.replaceAll("@" + i + "@", arg);
                }

                result += tmp;
            } else {
                // no separator, replace the template placeholder with the whole line
                result += rpc.template.replaceAll("@0@", inputLine);
            }

            result += "\n";
        }

        return new TemplateRespRPC(result);
    }
}
