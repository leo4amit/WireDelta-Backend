package com.wiredelta.backend.services;

import com.wiredelta.backend.exception.UserException;
import com.wiredelta.backend.models.request.LoginRequest;
import com.wiredelta.backend.models.response.LoginResponse;

public interface UserService {

    LoginResponse doLogin(LoginRequest loginRequest) throws UserException;

    void verifyToken(String accessToken) throws UserException;

}
