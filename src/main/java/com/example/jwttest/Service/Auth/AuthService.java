package com.example.jwttest.Service.Auth;

import com.example.jwttest.DTO.Auth.JwtResponse;
import com.example.jwttest.DTO.Auth.RegisterRequest;
import com.example.jwttest.Entity.User;

public interface AuthService {
    JwtResponse authenticateUser(String username, String password);
    void validateToken(String token);
    void registerUser(RegisterRequest registerRequest);
    void test();
    User getUser(String username);
}
