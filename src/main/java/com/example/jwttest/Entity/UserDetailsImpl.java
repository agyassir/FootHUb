package com.example.jwttest.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final Long id; // Custom field for user ID
    private final String username;
    private final String email; // Custom field for email
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    // Constructor
    public UserDetailsImpl(Long id, String username, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Static method to build UserDetailsImpl from a User entity
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    // Overridden methods from UserDetails interface
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize based on your application's requirements
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize based on your application's requirements
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize based on your application's requirements
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize based on your application's requirements
    }

    // Custom getters for additional fields
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}