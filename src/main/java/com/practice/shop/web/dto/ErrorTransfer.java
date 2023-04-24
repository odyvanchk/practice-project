package com.practice.shop.web.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

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
