package de.couchkiwi.enigmahub.model.enigma;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class EnigmaRequest implements Serializable {
    private String idToken;
    private String receiverModell;
    private List<String> capabilities;
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getReceiverModell() {
        return receiverModell;
    }

    public void setReceiverModell(String receiverModell) {
        this.receiverModell = receiverModell;
    }
}
