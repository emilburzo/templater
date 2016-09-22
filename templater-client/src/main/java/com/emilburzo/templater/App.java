package com.emilburzo.templater;

import com.emilburzo.templater.widget.Templater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    private static final String CONTENT_DIV_ID = "content";

    public void onModuleLoad() {
        RootPanel.get(CONTENT_DIV_ID).add(new Templater());
    }
}
