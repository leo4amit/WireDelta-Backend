package com.wiredelta.backend.services.impl;

import com.wiredelta.backend.entity.AccessToken;
import com.wiredelta.backend.entity.User;
import com.wiredelta.backend.errorCodes.WireDeltaError;
import com.wiredelta.backend.exception.UserException;
import com.wiredelta.backend.repository.TokenRepository;
import com.wiredelta.backend.services.TokenService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${wirdelta.token.enc.key}")
    private String apiKey;

    @Value("${wirdelta.token.expiry.seconds}")
    private int ttl;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public String generateToken(User userEntity) {

        List<AccessToken> accessTokenList = tokenRepository.findByUser(userEntity);

        if (accessTokenList != null) {
            for (AccessToken accessToken:accessTokenList) {
                Date accTokenDate = new Date(accessToken.getTtl());
                Date date = new Date();
                if (date.compareTo(accTokenDate) < 0) {
                    return accessToken.getToken();
                }
            }
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(userEntity.getUserId().toString())
                .setIssuedAt(now)
                .setIssuer(userEntity.getEmail())
                .setSubject(userEntity.getUserRole().getRole())
                .signWith(signingKey, signatureAlgorithm);

        Calendar cl = Calendar.getInstance();
        cl.add(Calendar.SECOND, ttl);
        Date exp = cl.getTime();
        builder.setExpiration(exp);

        String tokenString = builder.compact();

        saveToken(tokenString, userEntity, exp.getTime());
        return tokenString;
    }

    @Override
    public String parseToken(String jwtToken) throws UserException {

        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiKey))
                .build();

        try {
            String role = jwtParser.parseClaimsJws(jwtToken).getBody().getSubject();
            System.out.println("role is :" + role);
            return role;
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("toke is expired : {}", expiredJwtException);
            throw new UserException(WireDeltaError.WD_ERR_1004);
        } catch (UnsupportedJwtException | MalformedJwtException expiredJwtException) {
            log.error("toke is expired : {}", expiredJwtException);
            throw new UserException(WireDeltaError.WD_ERR_1005);
        }

    }


    private void saveToken(String tokenString, User user, Long ttl) {
        AccessToken token = new AccessToken();
        token.setToken(tokenString);
        token.setUser(user);
        token.setTtl(ttl);
        tokenRepository.save(token);
    }

}
