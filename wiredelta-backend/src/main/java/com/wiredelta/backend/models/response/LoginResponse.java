package com.wiredelta.backend.models.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String userName;
    private String userRole;
    private String accessToken;
    private String profilePicUrl;
    private String email;
}
