package com.emilburzo.templater.transformer;

import org.apache.commons.lang3.text.WordUtils;

public class TitlecaseTransformer extends AbstractTransformer {

    @Override
    public String transform(String body, String arg, int argNumber, int lineNumber) {
        return body.replaceAll("@title" + argNumber + "@", WordUtils.capitalizeFully(arg));
    }
}
