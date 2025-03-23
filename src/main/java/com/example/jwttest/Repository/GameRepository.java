package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
}
