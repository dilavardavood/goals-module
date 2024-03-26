package com.service.goals.utils;

public class Response<T> {
    private String status;
    private String error;
    private int statusCode;
    private T result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    // Constructor
    public Response(String status, String error, int statusCode, T result) {
        this.status = status;
        this.error = error;
        this.statusCode = statusCode;
        this.result = result;
    }

}
