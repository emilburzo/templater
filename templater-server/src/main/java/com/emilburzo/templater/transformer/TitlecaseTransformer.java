package com.emilburzo.templater.transformer;

import org.apache.commons.lang3.StringUtils;

public class TitlecaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int i) {
        return body.replaceAll("@title" + i + "@", StringUtils.capitalize(arg));
    }
}
