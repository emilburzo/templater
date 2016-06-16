package com.emilburzo.templater.transformer;

public class UppercaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int i) {
        return body.replaceAll("@upper" + i + "@", arg.toUpperCase());
    }

}
