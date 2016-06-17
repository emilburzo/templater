package com.emilburzo.templater.transformer;

public class UppercaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replaceAll("@upper" + argNumber + "@", arg.toUpperCase());
    }

}
