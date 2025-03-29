package com.example.jwttest.Service.Game;

import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.DTO.Game.GameDTO;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Repository.ClubRepository;
import com.example.jwttest.Repository.GameRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private ClubRepository clubRepository;
    private final ModelMapper modelMapper;

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

    public List<GameDTO> getGamesByDate(Date date) {
        return gameRepository.findAllByDate(date).stream().map((element) -> modelMapper.map(element, GameDTO.class)).collect(Collectors.toList());
    }

    public String getTodaysGame(){
        List <Game> games= gameRepository.findToday();
        return convertToArrayJson(games);
    }

    public GameDTO getUpcomingMatch(long id){
        return gameRepository.findNextUpcomingMatchByClubId(id).map((element) -> modelMapper.map(element, GameDTO.class)).orElse(null);
    }

    public String getGamesByHomeTeam(ClubDTO homeTeamId) {
        return gameRepository.findByHomeTeam(modelMapper.map(homeTeamId, Club.class)).stream()
                .map(this::convertGameListToString).collect(Collectors.joining());
    }

    public String getGamesByAwayTeam(ClubDTO awayTeamId) {
        return gameRepository.findByAwayTeam(modelMapper.map(awayTeamId, Club.class)).stream()
                .map(this::convertGameListToString).collect(Collectors.joining());
    }

    public List<GameDTO> getGamesByClub(long id){
        Club club = clubRepository.findById(id).orElse(null);
        return gameRepository.findByHomeTeamOrAwayTeam(club, club).stream()
                .map(game -> modelMapper.map(game, GameDTO.class)).collect(Collectors.toList());
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
