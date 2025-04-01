package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    List<Stadium> findByCapacityGreaterThan(int capacity);
    // Add custom queries as needed
}