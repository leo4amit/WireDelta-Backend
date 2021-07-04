package com.wiredelta.backend.services;

import com.wiredelta.backend.entity.User;
import com.wiredelta.backend.exception.UserException;

public interface TokenService {

    String generateToken(User userEntity);

    String parseToken(String jwtToken) throws UserException;

}
