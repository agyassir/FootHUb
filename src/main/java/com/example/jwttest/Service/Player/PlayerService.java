package com.example.jwttest.Service.Player;

import com.example.jwttest.DTO.Player.PLayerRequest;
import com.example.jwttest.DTO.Player.PlayerDTO;
import com.example.jwttest.Entity.Player;
import com.example.jwttest.Entity.ENUM.Position;

import java.util.Date;

public interface PlayerService {

         String getAllPlayers() ;

       PlayerDTO getPlayerById(Long id);
    String getPlayersByAge(int age);
    String getPlayersByName(String name);
    String getPlayersByClub (long id);
    String getPlayersByPosition(Position position);
    String getPlayersByNationality(String nationality);
    String getPlayersByMarketValue(double minMarketValue, double maxMarketValue);
    String getPlayersByContractUntil(Date contractUntil);
    String getPlayersByGoals(int goals);
    String getPlayersByAssists(int assists);
    String getPlayersByYellowCards();
    String getPlayersByGoalsInYear(int goals,int year);
    Player savePlayer(PLayerRequest player) ;
    Player updatePlayer(PLayerRequest player, long id) ;
    PlayerDTO getTrendingPlayer();
    void deletePlayer(Long id);
     String convertClubListToString(Player clubs);
    }

