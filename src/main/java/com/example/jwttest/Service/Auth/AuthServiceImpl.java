package com.example.jwttest.Service.Auth;

import com.example.jwttest.DTO.Auth.JwtResponse;
import com.example.jwttest.DTO.Auth.RegisterRequest;
import com.example.jwttest.Entity.ENUM.ERole;
import com.example.jwttest.Entity.Role;
import com.example.jwttest.Entity.User;
import com.example.jwttest.Repository.RoleRepository;
import com.example.jwttest.Repository.UserRepository;
import com.example.jwttest.Service.Jwt.UserDetailsServiceImpl;
import com.example.jwttest.Utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils,
                           UserDetailsServiceImpl userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public JwtResponse authenticateUser(String username, String password) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtUtils.generateJwtToken(authentication);

        // Get the authenticated user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Return the JWT token and user details
        return new JwtResponse(jwtToken, userDetails);
    }

    @Override
    public void validateToken(String token) {
        // Validate the JWT token
        if (!jwtUtils.validateJwtToken(token)) {
            throw new RuntimeException("Invalid JWT token");
        }

        // Extract the username from the token
        String username = jwtUtils.getUserNameFromJwtToken(token);

        // Load the user details
        UserDetails  userDetails = userDetailsService.loadUserByUsername(username);

        // Create an Authentication object and set it in the SecurityContext
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        // Check if the username or email is already taken
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        // Create a new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Fetch or create the default role (e.g., ROLE_USER)
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(ERole.ROLE_USER);
                    return roleRepository.save(role); // Save the role if it doesn't exist
                });

        // Assign the role to the user
        user.setRoles(Set.of(userRole));

        // Save the user to the database
        userRepository.save(user);
    }

    @Override
    public void test(){
        if (userRepository.existsByEmail("agyassir@gmail.com")) {
            throw new RuntimeException("Email is already in use!");
    }
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}