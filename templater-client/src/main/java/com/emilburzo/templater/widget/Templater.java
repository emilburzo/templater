package com.emilburzo.templater.widget;

import com.emilburzo.templater.pojo.TemplateReqRPC;
import com.emilburzo.templater.pojo.TemplateRespRPC;
import com.emilburzo.templater.rpc.RPC;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

public class Templater extends SimplePanel {

    interface MyUiBinder extends UiBinder<Widget, Templater> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField
    TextArea fieldInputData;
    @UiField
    TextArea fieldTemplate;
    @UiField
    TextBox fieldSeparator;
    @UiField
    Button btnGo;
    @UiField
    TextArea fieldResult;

    public Templater() {
        initUi();

        focusInput();
    }

    private void initUi() {
        setWidget(uiBinder.createAndBindUi(this));
    }

    /**
     * When the page is done loading, focus the data input field
     */
    private void focusInput() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            public void execute() {
                fieldInputData.setFocus(true);
            }
        });
    }

    @UiHandler("btnGo")
    public void onGo(ClickEvent event) {
        TemplateReqRPC rpc = new TemplateReqRPC();

        rpc.inputData = fieldInputData.getText();
        rpc.template = fieldTemplate.getText();
        rpc.separator = fieldSeparator.getText();

        RPC.service.process(rpc, new AsyncCallback<TemplateRespRPC>() {

            @Override
            public void onFailure(Throwable caught) {

            }

            @Override
            public void onSuccess(TemplateRespRPC result) {
                fieldResult.setText(result.result);
            }
        });
    }

}
