package com.somos_sansa.sansa.config.security;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.security.Key;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JWTAuthenticationConfig {
    private static final Key SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // Generates a secure key for HS512

    public static Key getSigningKey() {
        return SIGNING_KEY;
    }
    
    public String getJWTToken(String email) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");

        String token = Jwts
                .builder()
                .setId("userJWT")
                .setSubject(email)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512) // Use the static key for signing
                .compact();

        return TOKEN_BEARER_PREFIX + token; 
    }
}