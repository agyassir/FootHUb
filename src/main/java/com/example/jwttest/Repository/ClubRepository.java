package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findTop6ByOrderByPopularityScoreDesc();
}

