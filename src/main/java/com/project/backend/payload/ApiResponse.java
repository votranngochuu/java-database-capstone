package com.project.backend.payload;

import java.time.Instant;
import java.util.Map;

public class ApiResponse {
    private String status;
    private String message;
    private Object data;
    private Instant timestamp = Instant.now();

    public static ApiResponse success(String message, Object data) {
        ApiResponse r = new ApiResponse();
        r.status = "success";
        r.message = message;
        r.data = data;
        return r;
    }

    public static ApiResponse error(String message) {
        ApiResponse r = new ApiResponse();
        r.status = "error";
        r.message = message;
        r.data = Map.of();
        return r;
    }

    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public Object getData() { return data; }
    public Instant getTimestamp() { return timestamp; }
}
