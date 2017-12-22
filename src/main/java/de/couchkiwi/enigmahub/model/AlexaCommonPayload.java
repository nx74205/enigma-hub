package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AlexaCommonPayload implements Serializable {

    @JsonProperty("scope")
    AlexaCommonScope scope;

    public AlexaCommonScope getScope() {
        return scope;
    }

    public void setScope(AlexaCommonScope scope) {
        this.scope = scope;
    }
}
