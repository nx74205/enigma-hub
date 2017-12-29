package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlexaCommonHeader implements Serializable {
    private String namespace;
    private String name;
    private String payloadVersion;
    private String messageId;
    private String CorrelationToken;

    public String getCorrelationToken() {
        return CorrelationToken;
    }

    public void setCorrelationToken(String correlationToken) {
        CorrelationToken = correlationToken;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayloadVersion() {
        return payloadVersion;
    }

    public void setPayloadVersion(String payloadVersion) {
        this.payloadVersion = payloadVersion;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
