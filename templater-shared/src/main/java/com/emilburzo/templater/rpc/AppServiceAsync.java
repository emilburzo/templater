package com.emilburzo.templater.rpc;

import com.emilburzo.templater.pojo.TemplateReqRPC;
import com.emilburzo.templater.pojo.TemplateRespRPC;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>AppService</code>.
 */
public interface AppServiceAsync {

    void process(TemplateReqRPC rpc, AsyncCallback<TemplateRespRPC> callback);

}
