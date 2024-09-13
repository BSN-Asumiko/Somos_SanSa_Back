package com.somos_sansa.sansa.controllers;

import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.somos_sansa.sansa.config.security.JWTAuthenticationConfig;
import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.mapper.EntityToDTOMapper;
import com.somos_sansa.sansa.models.dto.UserDTO;
import com.somos_sansa.sansa.models.dto.auth.LoginRequest;
import com.somos_sansa.sansa.models.dto.auth.LoginResponse;
import com.somos_sansa.sansa.models.entities.User;
import com.somos_sansa.sansa.services.UserService;

@RestController
public class UserController {
    private final UserService userService;
    private final JWTAuthenticationConfig jwtAuthenticationConfig;

    public UserController(UserService userService, JWTAuthenticationConfig jwtAuthenticationConfig) {
        this.userService = userService;
        this.jwtAuthenticationConfig = jwtAuthenticationConfig;
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws SanSaException {
        Optional<User> user = userService.getUserByEmail(loginRequest.getEmail());

        if (user.isPresent()) {
            User finalUser = user.get();
            String token = jwtAuthenticationConfig.getJWTToken(loginRequest.getEmail());

            UserDTO resultDTO = EntityToDTOMapper.convertToUserDTO(finalUser);

            LoginResponse response = new LoginResponse(token, resultDTO);

            return ResponseEntity.ok(response);
        } else {
            throw new SanSaException("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(SIGNIN_URL)
    public ResponseEntity<?> signIn(@RequestBody User user) throws SanSaException {
        return userService.addNewUser(user);
    }

    @PutMapping(UPDATE_PROFILE_URL)
    public ResponseEntity<Object> updateProfile(@PathVariable int userId, @RequestBody User user)
            throws SanSaException {
        user.setId(userId);
        userService.updateUserProfile(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_PROFILE_DETAILS_URL)
    public ResponseEntity<UserDTO> getUserDetailsById(@PathVariable int id) throws SanSaException {
        User userDetails = userService.getUserById(id);
        if (userDetails == null) {
            throw new SanSaException("Usuario no encontrado");
        }
        UserDTO userDTO = EntityToDTOMapper.convertToUserDTO(userDetails);
        return ResponseEntity.ok(userDTO);
    }
}
