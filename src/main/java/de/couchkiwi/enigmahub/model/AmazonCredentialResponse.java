package de.couchkiwi.enigmahub.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class AmazonCredentialResponse implements Serializable {

    private String user_id;
    private String name;
    private String email;
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    boolean validCredential;

    public AmazonCredentialResponse() {
        this.validCredential = true;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public boolean isValidCredential() {
        return validCredential;
    }

    public void setValidCredential(boolean validCredential) {
        this.validCredential = validCredential;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
