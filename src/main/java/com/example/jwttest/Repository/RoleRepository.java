package com.example.jwttest.Repository;

import com.example.jwttest.Entity.ENUM.ERole;
import com.example.jwttest.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    public Optional<Role> findByName(ERole name);

}
