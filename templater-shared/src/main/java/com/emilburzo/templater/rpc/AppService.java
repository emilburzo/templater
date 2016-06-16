package com.emilburzo.templater.rpc;

import com.emilburzo.templater.pojo.TemplateReqRPC;
import com.emilburzo.templater.pojo.TemplateRespRPC;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("rpc")
public interface AppService extends RemoteService {

    TemplateRespRPC process(TemplateReqRPC rpc);

}
