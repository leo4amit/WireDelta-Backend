package com.wiredelta.backend.exception;

import lombok.Data;

@Data
public class WireDeltaException extends Exception {
    private String errorCode;
    private String errorMessage;

    WireDeltaException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
