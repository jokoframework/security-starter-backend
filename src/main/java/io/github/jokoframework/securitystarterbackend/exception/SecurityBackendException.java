package io.github.jokoframework.securitystarterbackend.exception;

import java.io.Serializable;

public class SecurityBackendException extends Exception implements Serializable {
    private static final long serialVersionUID = 8267555563351075537L;
    private String message;

    public SecurityBackendException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SecurityBackendException [message=" + message + "]";
    }
}
