package com.emilburzo.templater.rpc;

import com.emilburzo.templater.pojo.TemplateReqRPC;
import com.emilburzo.templater.pojo.TemplateRespRPC;
import com.emilburzo.templater.transformer.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.ArrayList;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    private static final List<AbstractTransformer> transformers = new ArrayList<>();

    // todo load dynamically using reflection?
    static {
        transformers.add(new BasicTransformer());
        transformers.add(new UppercaseTransformer());
        transformers.add(new LowercaseTransformer());
        transformers.add(new TitlecaseTransformer());
        transformers.add(new ReverseTransformer());
        transformers.add(new SwapcaseTransformer());
    }

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

                    tmp = transform(tmp, arg, i);
                }

                result += tmp;
            } else {
                // no separator, replace the template placeholder with the whole line
                result += transform(rpc.template, inputLine, 0);
            }

            result += "\n";
        }

        return new TemplateRespRPC(result);
    }

    private String transform(String body, String arg, int i) {
        String result = body;

        // apply all transformers
        for (AbstractTransformer transformer : transformers) {
            result = transformer.transform(result, arg, i);
        }

        return result;
    }
}
