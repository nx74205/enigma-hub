package de.couchkiwi.enigmahub.model;

import java.io.Serializable;

public class EnigmaRequest implements Serializable {
    private String alexaCommand;
    private String api;
    private String parameter;
    private String okText;
    private String errorText;
    private String nextAlexaCommand;

    // Getter & Setter

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        if (okText == null || okText.equals("")) {
            this.okText = "OK";
        } else {
            this.okText = okText;
        }
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        if (errorText == null || errorText.equals("")) {
            this.errorText = "OK";
        } else {
            this.errorText = errorText;
        }
    }

    public String getAlexaCommand() {
        return alexaCommand;
    }

    public void setAlexaCommand(String alexaCommand) {
        this.alexaCommand = alexaCommand;
    }

    public String getNextAlexaCommand() {
        return nextAlexaCommand;
    }

    public void setNextAlexaCommand(String nextAlexaCommand) {
        this.nextAlexaCommand = nextAlexaCommand;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        if (parameter == null)
            this.parameter = "";
        else
            this.parameter = parameter;
    }

}
