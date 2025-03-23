package com.example.jwttest.DTO.Auth;



import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class JwtResponse {
    private String token;
    private UserDetails user;

    public JwtResponse(String token, UserDetails user) {
        this.token = token;
        this.user = user;
    }

}