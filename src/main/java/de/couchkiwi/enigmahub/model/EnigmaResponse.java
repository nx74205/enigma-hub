package de.couchkiwi.enigmahub.model;

import java.io.Serializable;

public class EnigmaResponse implements Serializable {
    private boolean instandby;
    private boolean result;
    private int current;
    private String message;
    private boolean ismute;

    public boolean isInstandby() {
        return instandby;
    }

    public void setInstandby(boolean instandby) {
        this.instandby = instandby;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsmute() {
        return ismute;
    }

    public void setIsmute(boolean ismute) {
        this.ismute = ismute;
    }
}
