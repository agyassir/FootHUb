package com.example.jwttest.Config;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface UserDetails extends Serializable {
    // Returns the authorities granted to the user
    Collection<? extends GrantedAuthority> getAuthorities();

    // Returns the password used to authenticate the user
    String getPassword();

    // Returns the username used to authenticate the user
    String getUsername();

    // Indicates whether the user's account has expired
    boolean isAccountNonExpired();

    // Indicates whether the user is locked or unlocked
    boolean isAccountNonLocked();

    // Indicates whether the user's credentials (password) have expired
    boolean isCredentialsNonExpired();

    // Indicates whether the user is enabled or disabled
    boolean isEnabled();
}