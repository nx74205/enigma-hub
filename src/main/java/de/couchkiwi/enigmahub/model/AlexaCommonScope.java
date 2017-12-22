package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AlexaCommonScope implements Serializable {

    @JsonProperty("type")
    String stype;
    String token;

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
