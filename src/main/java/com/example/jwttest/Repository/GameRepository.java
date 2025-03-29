package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Long> {
    @Query("SELECT g FROM Game g WHERE DATE(g.date) = :date")
    List<Game> findAllByDate(@Param("date") Date date);
    List<Game> findByAwayTeam(Club awayTeam);
    List<Game> findByHomeTeam(Club homeTeam);
    @Query("SELECT g FROM Game g " +
            "WHERE g.date = CURRENT_DATE "+
            "ORDER BY (g.homeTeam.popularityScore + g.awayTeam.popularityScore) DESC LIMIT 4")
    List<Game> findHotMatchesOfToday();

    @Query("SELECT g FROM Game g WHERE FUNCTION('DATE', g.date) = CURRENT_DATE")
    List<Game> findToday();

    @Query("SELECT m FROM Game m " +
            "WHERE (m.homeTeam.id = :clubId OR m.awayTeam.id = :clubId) " +
            "AND m.date > CURRENT_TIMESTAMP " +
            "ORDER BY m.date ASC limit 1")
    Optional<Game> findNextUpcomingMatchByClubId(@Param("clubId") Long clubId);
    List<Game> findByHomeTeamOrAwayTeam(Club homeTeam, Club awayTeam);
}
