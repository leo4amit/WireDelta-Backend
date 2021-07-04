package com.wiredelta.backend.models.error;

import lombok.Data;

@Data
public class Error {

    private String errorCode;

    private String errorMessage;
}
