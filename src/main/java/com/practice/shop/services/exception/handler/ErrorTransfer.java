package com.practice.shop.services.exception.handler;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorTransfer {

    private Map<String, String> errors;

    public ErrorTransfer() {
        errors = new HashMap<>();
    }

    public ErrorTransfer(String errorMessage) {
        this();
        errors.put("msg", errorMessage);
    }

    public void put(String key, String value) {
        errors.put(key, value);
    }


}
