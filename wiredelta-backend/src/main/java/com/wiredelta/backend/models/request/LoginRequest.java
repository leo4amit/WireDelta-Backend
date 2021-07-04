package com.wiredelta.backend.models.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "userName is mandatory")
    String userName;

    @NotBlank(message = "password is mandatory")
    String password;
}
