package com.emilburzo.templater.rpc;

import com.google.gwt.core.client.GWT;

public class RPC {

    public static final AppServiceAsync service = GWT.create(AppService.class);

}
