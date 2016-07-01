package com.emilburzo.templater.transformer;

import org.apache.commons.lang3.StringUtils;

public class SwapcaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replace("@swapcase" + argNumber + "@", StringUtils.swapCase(arg));
    }

}
