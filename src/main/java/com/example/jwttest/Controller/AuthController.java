package com.example.jwttest.Controller;


import com.example.jwttest.DTO.Auth.JwtResponse;
import com.example.jwttest.DTO.Auth.LoginRequest;
import com.example.jwttest.DTO.Auth.RegisterRequest;
import com.example.jwttest.Service.Auth.AuthService;
import com.example.jwttest.Utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private JwtUtils jwtUtil;

    public AuthController(AuthService authService,JwtUtils jwtUtil) {
        this.authService = authService;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwt = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        authService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }

        // Extract the token (remove "Bearer " prefix and trim any spaces)
        String jwtToken = token.substring(7).trim();

        // Validate the token
        if (!jwtUtil.validateJwtToken(jwtToken)) {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }

        String username=jwtUtil.getUserNameFromJwtToken(jwtToken);
        return ResponseEntity.ok(authService.getUser(username));
    }
}
