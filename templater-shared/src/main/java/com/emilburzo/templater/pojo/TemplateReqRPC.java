package com.emilburzo.templater.pojo;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public class TemplateReqRPC implements Serializable, IsSerializable {

    public String inputData;
    public String template;
    public String separator;

}
