package com.personnalize_design.meals.data.model;

public class ServerResponse {

    private String message;

    public ServerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
