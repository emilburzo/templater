package com.emilburzo.templater.transformer;

public class LineNumberTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replaceAll("@num@", String.valueOf(lineNumber));
    }

}
