package com.example.jwttest.Repository;

import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Player;
import com.example.jwttest.Entity.ENUM.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByFirstNameOrLastName(String firstName, String lastName);
    List<Player> findByClub(Club club);
    List<Player> findByPosition(Position position);
    List<Player> findByAge(int age);
    List<Player> findByNationality(String nationality);
    List<Player> findByMarketValueBetween(double minMarketValue, double maxMarketValue);
    List<Player> findByContractUntilBefore(Date contractUntil);
    Player findByTrendingTrue();
    @Query( "SELECT p FROM Player p LEFT JOIN FETCH p.stats ps WHERE p.trending = true AND ps.season = '24-25'")
    Player findTrendyPlayerWithStats();
}

