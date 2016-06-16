package com.emilburzo.templater;

import com.emilburzo.templater.widget.Templater;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint {

    public void onModuleLoad() {
        RootPanel.get("content").add(new Templater());
    }
}
