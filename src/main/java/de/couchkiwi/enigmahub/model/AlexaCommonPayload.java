package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class AlexaCommonPayload implements Serializable {

    @JsonProperty("scope")
    AlexaCommonScope scope;
    List<AlexaCommonEndpoints.Endpoints> endpoint;

    public AlexaCommonScope getScope() {
        return scope;
    }

    public void setScope(AlexaCommonScope scope) {
        this.scope = scope;
    }

    public List<AlexaCommonEndpoints.Endpoints> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List<AlexaCommonEndpoints.Endpoints> endpoint) {
        this.endpoint = endpoint;
    }
}

