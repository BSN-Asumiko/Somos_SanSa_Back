package com.somos_sansa.sansa.controllers;

import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.somos_sansa.sansa.config.security.JWTAuthenticationConfig;
import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.models.dto.auth.LoginRequest;
import com.somos_sansa.sansa.models.entities.User;
import com.somos_sansa.sansa.services.UserService;

import io.jsonwebtoken.io.IOException;

@RestController
public class UserController {
    JWTAuthenticationConfig jwtAuthenticationConfig = new JWTAuthenticationConfig();

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(LOGIN_URL)
    public User login(
        @RequestBody LoginRequest loginRequest
    ) {
        User finalUser = null;
        Optional<User> user = userService.getUserByEmail(loginRequest.getEmail());
        if(user.isPresent()){
            String token = jwtAuthenticationConfig.getJWTToken(loginRequest.getEmail());
            finalUser = user.get();
            finalUser.setToken(token);
        } else {
            throw new IOException("FAIL Authentication");
        }
        return finalUser;
    }

    @PostMapping(SIGNIN_URL)
    public ResponseEntity<?> signIn(@RequestBody User user) throws SanSaException{
        return userService.addNewUser(user);
    }

    @PutMapping(UPDATE_PROFILE_URL)
    public ResponseEntity<Object> updateProfile (@PathVariable int id, @RequestBody User user) throws SanSaException {
        user.setId(id);
        userService.updateUserProfile(user);
        return ResponseEntity.ok().build();
    }
}
