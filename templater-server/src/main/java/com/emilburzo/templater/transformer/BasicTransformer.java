package com.emilburzo.templater.transformer;

public class BasicTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int i) {
        return body.replaceAll("@" + i + "@", arg);
    }
}
