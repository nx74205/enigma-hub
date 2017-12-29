package de.couchkiwi.enigmahub.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlexaPowerResponse implements Serializable {

    private PowerContext context;
    private PowerEvent event;

    public PowerContext getContext() {
        return context;
    }

    public void setContext(PowerContext context) {
        this.context = context;
    }

    public PowerEvent getEvent() {
        return event;
    }

    public void setEvent(PowerEvent event) {
        this.event = event;
    }

    public static class PowerContext {
        private PowerContextProperties[] properties;

        public PowerContextProperties[] getProperties() {
            return properties;
        }

        public void setProperties(PowerContextProperties[] properties) {
            this.properties = properties;
        }

        public static class PowerContextProperties {
            private String namespace;
            private String name;
            private String value;
            private String timeOfSample;
            private int uncertaintyInMilliseconds;

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

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getTimeOfSample() {
                return timeOfSample;
            }

            public void setTimeOfSample(String timeOfSample) {
                this.timeOfSample = timeOfSample;
            }

            public int getUncertaintyInMilliseconds() {
                return uncertaintyInMilliseconds;
            }

            public void setUncertaintyInMilliseconds(int uncertaintyInMilliseconds) {
                this.uncertaintyInMilliseconds = uncertaintyInMilliseconds;
            }
        }
    }

    public static class PowerEvent {

        private PowerEventHeader header;
        private PowerEventEndpoint endpoint;
        private PowerEventPayload payload;

        public PowerEventPayload getPayload() { return payload; }
        public void setPayload(PowerEventPayload payload) { this.payload = payload; }

        public PowerEventHeader getHeader() {
            return header;
        }

        public void setHeader(PowerEventHeader header) {
            this.header = header;
        }

        public PowerEventEndpoint getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(PowerEventEndpoint endpoint) {
            this.endpoint = endpoint;
        }

        public static class PowerEventHeader {
            private String namespace;
            private String name;
            private String payloadVersion;
            private String messageId;
            private String correlationToken;

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

            public String getCorrelationToken() {
                return correlationToken;
            }

            public void setCorrelationToken(String correlationToken) {
                this.correlationToken = correlationToken;
            }
        }

        public static class PowerEventEndpoint {
            String endpointId;

            public String getEndpointId() {
                return endpointId;
            }

            public void setEndpointId(String endpointId) {
                this.endpointId = endpointId;
            }
        }
        public static class PowerEventPayload {
            private String type;
            private String message;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }

}

/*
{
  "context": {
    "properties": [ {
      "namespace": "Alexa.PowerController",
      "name": "powerState",
      "value": "ON",
      "timeOfSample": "2017-02-03T16:20:50.52Z",
      "uncertaintyInMilliseconds": 500
    } ]
  },
  "event": {
    "header": {
      "namespace": "Alexa",
      "name": "Response",
      "payloadVersion": "3",
      "messageId": "5f8a426e-01e4-4cc9-8b79-65f8bd0fd8a4",
      "correlationToken": "dFMb0z+PgpgdDmluhJ1LddFvSqZ/jCc8ptlAKulUj90jSqg=="
    },
    "endpoint": {
      "scope": {
        "type": "BearerToken",
        "token": "access-token-from-Amazon"
      },
      "endpointId": "appliance-001"
    },
    "payload": {}
  }
}
 */