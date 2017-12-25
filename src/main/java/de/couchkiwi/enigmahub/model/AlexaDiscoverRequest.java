package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.couchkiwi.enigmahub.model.AlexaCommonHeader;
import de.couchkiwi.enigmahub.model.AlexaCommonPayload;

import java.io.Serializable;

public class AlexaDiscoverRequest implements Serializable {
    Directive directive;

    public Directive getDirective() {
        return directive;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }

    public static class Directive {

        @JsonProperty("header")
        AlexaCommonHeader header;

        @JsonProperty("payload")
        AlexaCommonPayload payload;

        public AlexaCommonHeader getHeader() {
            return header;
        }

        public void setHeader(AlexaCommonHeader header) {
            this.header = header;
        }

        public AlexaCommonPayload getPayload() {
            return payload;
        }

        public void setPayload(AlexaCommonPayload payload) {
            this.payload = payload;
        }
    }
    @Override
    public String toString() {
        return "Namespace/Name: " + this.getDirective().getHeader().getNamespace() + " " + this.getDirective().getHeader().getName() + "/n" +
               "Token:          " + this.getDirective().getPayload().getScope().getToken();

    }

}
