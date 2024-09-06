package com.somos_sansa.sansa.models.dto.auth;

import com.somos_sansa.sansa.models.dto.UserDTO;

public class LoginResponse {
    private String token;
    private UserDTO userDTO;

    public LoginResponse(String token, UserDTO userDTO) {
        this.token = token;
        this.userDTO = userDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    
}
