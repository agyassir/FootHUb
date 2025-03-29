package com.example.jwttest.Repository;

import com.example.jwttest.Entity.League;
import com.example.jwttest.Entity.LeagueSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueSeasonRepository extends JpaRepository<LeagueSeason,Long> {
    Optional<LeagueSeason> findBySeasonAndLeague(String season, League league);
}
