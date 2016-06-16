package com.emilburzo.templater.transformer;

import org.apache.commons.lang3.StringUtils;

public class ReverseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int i) {
        return body.replaceAll("@reverse" + i + "@", StringUtils.reverse(arg));
    }
}
