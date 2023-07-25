package com.example.ecommerce.configs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<Object> generateResponse( HttpStatus status, String message, Object res) {
        Map<String, Object> json = new HashMap<>();
        json.put("status", status.value());
        json.put("message", message);
        json.put("data", res);
        return new ResponseEntity<>(json, status);
    }
}
