package com.wiredelta.backend.interceptor;

import com.wiredelta.backend.errorCodes.WireDeltaError;
import com.wiredelta.backend.exception.UserException;
import com.wiredelta.backend.models.constants.WireDeltaConstants;
import com.wiredelta.backend.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class UserSessionInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("UserSessionInterceptor: " + request.getRequestURL());

        String jwtToken = request.getHeader(WireDeltaConstants.ACCESS_TOKEN_HEADER);

        if (jwtToken == null) {
            throw new UserException(WireDeltaError.WD_ERR_1003);
        }

        String role = tokenService.parseToken(jwtToken);

        if (!role.equalsIgnoreCase("Admin")) {
            throw new UserException(WireDeltaError.WD_ERR_1003);
        }

        return true;
    }
}
