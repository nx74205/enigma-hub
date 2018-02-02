package de.couchkiwi.enigmahub.model;

public class AlexaRegister {

    private String state;
    private String Client_id;
    private String response_type;
    private String Scope;
    private String dumbToken;
    private String uri;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClient_id() {
        return Client_id;
    }

    public void setClient_id(String client_id) {
        Client_id = client_id;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }

    public String getDumbToken() {
        return dumbToken;
    }

    public void setDumbToken(String dumbToken) {
        this.dumbToken = dumbToken;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
