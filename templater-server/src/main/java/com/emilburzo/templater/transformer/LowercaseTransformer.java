package com.emilburzo.templater.transformer;

public class LowercaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replaceAll("@lower" + argNumber + "@", arg.toLowerCase());
    }
}
