package de.couchkiwi.enigmahub.model;

import java.io.Serializable;

public class AmazonResponse implements Serializable {
    private String version;
    private Response response;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    static public class Response {
        private OutputSpeech outputSpeech;
        private boolean shouldEndSession;

        public OutputSpeech getOutputSpeech() {
            return outputSpeech;
        }

        public void setOutputSpeech(OutputSpeech outputSpeech) {
            this.outputSpeech = outputSpeech;
        }

        public boolean isShouldEndSession() {
            return shouldEndSession;
        }

        public void setShouldEndSession(boolean shouldEndSession) {
            this.shouldEndSession = shouldEndSession;
        }

        static public class OutputSpeech {
            private String type;
            private String text;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}
