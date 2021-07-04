package com.wiredelta.backend.services.impl;

import com.wiredelta.backend.entity.User;
import com.wiredelta.backend.errorCodes.WireDeltaError;
import com.wiredelta.backend.exception.UserException;
import com.wiredelta.backend.models.request.LoginRequest;
import com.wiredelta.backend.models.response.LoginResponse;
import com.wiredelta.backend.repository.UserRepository;
import com.wiredelta.backend.services.TokenService;
import com.wiredelta.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public LoginResponse doLogin(LoginRequest loginRequest) throws UserException {

        Optional<User> user = Optional.ofNullable(userRepository.findByUserName(loginRequest.getUserName()));

        if (user.isPresent()) {
            if(user.get().getPassword().equalsIgnoreCase(loginRequest.getPassword())) {
                LoginResponse loginResponse = generateLoginResponse(user.get());
                return loginResponse;
            }else{
                throw new UserException(WireDeltaError.WD_ERR_1007);
            }
        } else {
            throw new UserException(WireDeltaError.WD_ERR_1001);
        }
    }


    @Override
    public void verifyToken(String accessToken) throws UserException {
        tokenService.parseToken(accessToken);
    }

    private LoginResponse generateLoginResponse(User user) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserName(user.getUserName());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setProfilePicUrl(user.getProfilePic());

        String jwtToken = tokenService.generateToken(user);

        loginResponse.setAccessToken(jwtToken);
        loginResponse.setUserRole(user.getUserRole().getRole());
        return loginResponse;

    }
}
