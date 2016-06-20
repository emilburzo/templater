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
    Button btnExample;
    @UiField
    Button btnClear;
    @UiField
    Button btnGenerate;

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

         // nginx
        rpc = new TemplateReqRPC();
        rpc.inputData = "templater,172.13.1.2,8080\n" +
                "hnjobs,192.168.1.34,8080\n" +
                "ambient,10.10.7.2,8080\n";
        rpc.template = "server {\n" +
                "    listen          80;\n" +
                "    server_name     @0@.emilburzo.com;\n" +
                "\n" +
                "    location /.well-known {\n" +
                "        root        /srv/http/@0@.emilburzo.com;\n" +
                "    }\n" +
                "\n" +
                "    location / {\n" +
                "        return          301 https://$host$request_uri;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "server {\n" +
                "    listen                  443 ssl http2;\n" +
                "\n" +
                "    server_name             @0@.emilburzo.com;\n" +
                "\n" +
                "    root                    /srv/http/@0@.emilburzo.com;\n" +
                "    index                   index.php index.htm index.html;\n" +
                "\n" +
                "    access_log              /var/log/nginx/@0@.emilburzo.com-access.log;\n" +
                "    error_log               /var/log/nginx/@0@.emilburzo.com-error.log;\n" +
                "\n" +
                "    ssl                     on;\n" +
                "    ssl_certificate         /etc/letsencrypt/live/@0@.emilburzo.com/fullchain.pem;\n" +
                "    ssl_certificate_key     /etc/letsencrypt/live/@0@.emilburzo.com/privkey.pem;\n" +
                "\n" +
                "    location / {\n" +
                "        proxy_set_header    Host $host;\n" +
                "        proxy_set_header    X-Real-IP $remote_addr;\n" +
                "        proxy_pass          http://@1@:@2@;\n" +
                "    }\n" +
                "}\n";
        rpc.separator = ",";

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

    @UiHandler("btnExample")
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
