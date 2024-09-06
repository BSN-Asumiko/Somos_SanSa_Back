package com.somos_sansa.sansa.config.security;

import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.io.IOException;
import java.security.Key;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private static final Key SIGNING_KEY = JWTAuthenticationConfig.getSigningKey(); // Use the same static key

    private Claims parseClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private void setAuthentication(Claims claims) {
        Object authoritiesObj = claims.get("authorities");
        List<String> authorities = null;

        if (authoritiesObj instanceof List<?>) {
            authorities = ((List<?>) authoritiesObj).stream()
                            .filter(String.class::isInstance)
                            .map(String.class::cast)
                            .collect(Collectors.toList());
        }

        if (authorities != null) {
            UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private boolean isJWTValid(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZATION_KEY);
        if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
            return false;
        }
        // Additional checks for token validity can be added here if necessary
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (isJWTValid(request)) {
                String token = request.getHeader(HEADER_AUTHORIZATION_KEY).replace(TOKEN_BEARER_PREFIX, "");
                Claims claims = parseClaimsFromToken(token);
                if (claims.get("authorities") != null) {
                    setAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}
