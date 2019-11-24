package com.customer.web.response;

public class ApiResponse {
    private int status;
    private String message;

    public ApiResponse() {

    }

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}

