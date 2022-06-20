package com.practice.shop.services.exception.handler;

import lombok.Data;

@Data
public class ErrorTransfer {
    private String msg;

    public ErrorTransfer(String errorMessage) {
        this.msg = errorMessage;
    }
}
