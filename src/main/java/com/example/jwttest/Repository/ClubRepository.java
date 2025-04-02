package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> findTop6ByOrderByPopularityScoreDesc();
    @Query("SELECT c.league FROM Club c WHERE c.id = :clubId")
    Optional<League> findLeagueByClubId(@Param("clubId") Long clubId);
}

