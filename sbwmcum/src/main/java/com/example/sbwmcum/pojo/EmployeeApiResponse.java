package com.example.sbwmcum.pojo;

import org.json.JSONObject;

public class EmployeeApiResponse {

    private String status;
    private String message;
    private JSONObject data;

    public EmployeeApiResponse(String status, String message, JSONObject data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public EmployeeApiResponse() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
