package com.emilburzo.templater.transformer;

public class BasicTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replace("@" + argNumber + "@", arg);
    }
}
