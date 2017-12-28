package de.couchkiwi.enigmahub.model.enigma;

import java.io.Serializable;

public class EnigmaResponse implements Serializable {

    private String eMailAddress;
    private String receiverModell;
    private String alexaCommand;
    private String EnigmaApi;
    private String Enigmaparameter;

    public EnigmaResponse(String eMailAddress, String receiverModell, String alexaCommand, String enigmaApi, String enigmaparameter) {
        this.eMailAddress = eMailAddress;
        this.receiverModell = receiverModell;
        this.alexaCommand = alexaCommand;
        EnigmaApi = enigmaApi;
        Enigmaparameter = enigmaparameter;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public String getReceiverModell() {
        return receiverModell;
    }

    public void setReceiverModell(String receiverModell) {
        this.receiverModell = receiverModell;
    }

    public String getAlexaCommand() {
        return alexaCommand;
    }

    public void setAlexaCommand(String alexaCommand) {
        this.alexaCommand = alexaCommand;
    }

    public String getEnigmaApi() {
        return EnigmaApi;
    }

    public void setEnigmaApi(String enigmaApi) {
        EnigmaApi = enigmaApi;
    }

    public String getEnigmaparameter() {
        return Enigmaparameter;
    }

    public void setEnigmaparameter(String enigmaparameter) {
        Enigmaparameter = enigmaparameter;
    }
}
