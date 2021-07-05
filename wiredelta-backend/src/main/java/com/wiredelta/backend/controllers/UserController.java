package com.wiredelta.backend.controllers;

import com.wiredelta.backend.models.request.LoginRequest;
import com.wiredelta.backend.models.response.BaseResponse;
import com.wiredelta.backend.models.response.LoginResponse;
import com.wiredelta.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "login")
    public BaseResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        BaseResponse<LoginResponse> loginResponse=new BaseResponse<>();
        loginResponse.setData(userService.doLogin(loginRequest));
        return loginResponse;
    }

}
