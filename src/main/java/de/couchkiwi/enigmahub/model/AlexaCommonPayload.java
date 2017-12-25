package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlexaCommonPayload implements Serializable {

    @JsonProperty("scope")
    private AlexaCommonScope scope;
    private List<AlexaCommonEndpoints.Endpoints> endpoints;

    public AlexaCommonScope getScope() {
        return scope;
    }

    public void setScope(AlexaCommonScope scope) {
        this.scope = scope;
    }

    public List<AlexaCommonEndpoints.Endpoints> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<AlexaCommonEndpoints.Endpoints> endpoints) {
        this.endpoints = endpoints;
    }
}

