package com.example.jwttest.Service.Game;

import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Repository.GameRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public String getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::convertGameListToString).collect(Collectors.joining());
    }

    public String getGameById(Long id) {
    Game game=gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
    return convertGameListToString(game);
    }

    public String createGame(Game game) {
        return convertGameListToString(gameRepository.save(game));
    }

    public Game updateGame(Long id, Game game) {
        Game existingGame = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        existingGame.setDate(game.getDate());
        existingGame.setHomeTeam(game.getHomeTeam());
        existingGame.setAwayTeam(game.getAwayTeam());
        existingGame.setHomeScore(game.getHomeScore());
        existingGame.setAwayScore(game.getAwayScore());
        return gameRepository.save(existingGame);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    public String getGamesByDate(Date date) {
        return convertToArrayJson(gameRepository.findAllByDate(date));
    }

    public String getTodaysGame(){
        List <Game> games= gameRepository.findToday();
        return convertToArrayJson(games);
    }

    public String getGamesByHomeTeam(Club homeTeamId) {
        return gameRepository.findByHomeTeam(homeTeamId).stream()
                .map(this::convertGameListToString).collect(Collectors.joining());
    }

    public String getGamesByAwayTeam(Club awayTeamId) {
        return gameRepository.findByAwayTeam(awayTeamId).stream()
                .map(this::convertGameListToString).collect(Collectors.joining());
    }
    public String getHotGames() {
            List<Game> games = gameRepository.findHotMatchesOfToday();
     return convertToArrayJson(games);
    }
    public String convertGameListToString(Game clubs) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        try {
            return objectMapper.writeValueAsString(clubs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert clubs to JSON", e);
        }
    }

    public String convertToArrayJson(List<?> list){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        try{
            return objectMapper.writeValueAsString(list); // Converts the list to a JSON array
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert games to JSON", e);
        }
    }

}
