package com.mansourbens.user.exceptions;

public class WebApplicationException extends Exception {
    private static final long serialVersionUID = 1L;
    private final int error;
    private final String status;
    public WebApplicationException(final int code, final String message, final String status) {
        super(message);

        this.error = code;
        this.status = status;
    }
}
