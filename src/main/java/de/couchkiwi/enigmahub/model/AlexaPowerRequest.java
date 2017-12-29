package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AlexaPowerRequest implements Serializable {
    private Directive directive;

    public Directive getDirective() {
        return directive;
    }

    public void setDirective(Directive directive) {
        this.directive = directive;
    }

    public static class Directive {

        @JsonProperty("header")
        private AlexaCommonHeader header;

        @JsonProperty("endpoint")
        private AlexaEndpoint endpoint;

        public AlexaCommonHeader getHeader() {
            return header;
        }

        public void setHeader(AlexaCommonHeader header) {
            this.header = header;
        }

        public AlexaEndpoint getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(AlexaEndpoint endpoint) {
            this.endpoint = endpoint;
        }

        public static class AlexaEndpoint {
            private String endpointId;

            private AlexaScope scope;

            public String getEndpointId() {
                return endpointId;
            }

            public void setEndpointId(String endpointId) {
                this.endpointId = endpointId;
            }

            public AlexaScope getScope() {
                return scope;
            }

            public void setScope(AlexaScope scope) {
                this.scope = scope;
            }

            public static class AlexaScope {
                private String type;
                private String token;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }
            }
        }

    }
    @Override
    public String toString() {
        return "Namespace/Name: " + this.getDirective().getHeader().getNamespace() + " " + this.getDirective().getHeader().getName() + "/n" +
               "Endpoint:       " + this.getDirective().getEndpoint().getEndpointId();

    }

}

/*
{
  "directive": {
    "header": {
      "namespace": "Alexa.PowerController",
      "name": "TurnOn",
      "payloadVersion": "3",
      "messageId": "1bd5d003-31b9-476f-ad03-71d471922820",
      "correlationToken": "dFMb0z+PgpgdDmluhJ1LddFvSqZ/jCc8ptlAKulUj90jSqg=="
    },
    "endpoint": {
      "scope": {
        "type": "BearerToken",
        "token": "access-token-from-skill"
      },
      "endpointId": "appliance-001",
      "cookie": {}
    },
    "payload": {}
  }
}
 */