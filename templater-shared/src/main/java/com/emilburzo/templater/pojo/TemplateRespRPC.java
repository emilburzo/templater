package com.emilburzo.templater.pojo;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class TemplateRespRPC implements Serializable, IsSerializable {

    public String result;

    public TemplateRespRPC() {
    }

    public TemplateRespRPC(String result) {
        this.result = result;
    }
}
