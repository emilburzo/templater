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
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Row;
import org.gwtbootstrap3.client.ui.TextArea;
import org.gwtbootstrap3.client.ui.TextBox;

import java.util.ArrayList;
import java.util.List;

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
    TextArea fieldResult;

    @UiField
    Button btnRandomExample;
    @UiField
    Button btnClear;
    @UiField
    Button btnGenerate;
    @UiField
    Row rowResult;

    private List<TemplateReqRPC> suggestions = new ArrayList<>();

    public Templater() {
        initUi();

        loadSuggestions();

        focusInput();
    }

    private void loadSuggestions() {
        TemplateReqRPC rpc = null;

        // sql
        rpc = new TemplateReqRPC();
        rpc.inputData = "john,doe,EMployee,active,secret,bROKENcAMEL\n" +
                "JANE,dOE, staff,inactive,verysecret,bROKENcAMEL";
        rpc.template = "INSERT INTO records VALUES ('@num@', '@title0@', '@title1@', (SELECT id FROM types WHERE name = '@lower2@'), '@upper3@', '@reverse4@', '@swapcase5@');";
        rpc.separator = ",";

        suggestions.add(rpc);

        // linux
        rpc = new TemplateReqRPC();
        rpc.inputData = "joe\n" +
                "mary\n" +
                "watson\n" +
                "albert\n";
        rpc.template = "usermod -s /sbin/nologin @0@";
        rpc.separator = "";

        suggestions.add(rpc);
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

    @UiHandler("btnClear")
    public void onClear(ClickEvent event) {
        fieldInputData.clear();
        fieldTemplate.clear();
        fieldSeparator.clear();
        fieldResult.clear();
    }

    @UiHandler("btnRandomExample")
    public void onRandomExample(ClickEvent event) {
        // todo have a set of suggestions and load one randomly

        TemplateReqRPC suggestion = suggestions.get(Random.nextInt(suggestions.size()));

        fieldInputData.setText(suggestion.inputData);
        fieldTemplate.setText(suggestion.template);
        fieldSeparator.setText(suggestion.separator);

        onGenerate(null);
    }

    @UiHandler("btnGenerate")
    public void onGenerate(ClickEvent event) {
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
