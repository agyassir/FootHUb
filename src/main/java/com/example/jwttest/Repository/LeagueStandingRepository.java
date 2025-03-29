package com.example.jwttest.Repository;


import com.example.jwttest.Entity.LeagueSeason;
import com.example.jwttest.Entity.LeagueStanding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueStandingRepository extends JpaRepository<LeagueStanding,Long> {
    @Query(value = "SELECT * FROM league_standing " +
            "WHERE league_season_id = :seasonId",
            nativeQuery = true) // Changed to match JPA entity relationship
    List<LeagueStanding> findBySeason(
            @Param("seasonId") Long seasonId);
}
