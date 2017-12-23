package de.couchkiwi.enigmahub.model;

import java.io.Serializable;

public class AlexaDiscoverResponse implements Serializable {

    private AlexaDiscoverEvent event;

    public AlexaDiscoverEvent getEvent() {
        return event;
    }

    public void setEvent(AlexaDiscoverEvent event) {
        this.event = event;
    }

    public static class AlexaDiscoverEvent {

        private AlexaCommonHeader header;
        private AlexaCommonPayload payload;

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
}
