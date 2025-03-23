package com.example.jwttest.Repository;


import com.example.jwttest.Entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends JpaRepository<League,Long> {
    League findByName(String name);
    League findById(long id);

}
