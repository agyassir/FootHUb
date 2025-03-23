package com.example.jwttest.Service.Player;

import com.example.jwttest.DTO.Player.PLayerRequest;
import com.example.jwttest.DTO.Player.PlayerDTO;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Player;
import com.example.jwttest.Entity.ENUM.Position;
import com.example.jwttest.Repository.ClubRepository;
import com.example.jwttest.Repository.PlayerRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final ModelMapper modelMapper;

    public PlayerServiceImpl(PlayerRepository playerRepository, ClubRepository clubRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public String getAllPlayers() {
        return convertToArrayJson(playerRepository.findAll());
    }

    @Override
    public PlayerDTO getTrendingPlayer() {
        return modelMapper.map(playerRepository.findTrendyPlayerWithStats(), PlayerDTO.class);
    }

    @Override
    public String getPlayerById(Long id) {
        return this.convertClubListToString((playerRepository.findById(id).orElse(null)));
    }
    @Override
    public Player savePlayer(PLayerRequest player) {
        Player player1=modelMapper.map(player,Player.class);
        return playerRepository.save(player1);
    }
    @Override
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public String getPlayersByName(String name) {
        return playerRepository.findByFirstNameOrLastName(name,name).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByClub(long id) {
        Club club=clubRepository.findById(id).orElse(null);
        return playerRepository.findByClub(club).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByPosition(Position position) {
        return playerRepository.findByPosition(position).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByAge(int age) {
        return playerRepository.findByAge(age).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByNationality(String nationality) {
        return playerRepository.findByNationality(nationality).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByMarketValue(double minMarketValue, double maxMarketValue) {
        return playerRepository.findByMarketValueBetween(minMarketValue, maxMarketValue).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByContractUntil(Date contractUntil) {
        return playerRepository.findByContractUntilBefore(contractUntil).stream().map(this::convertClubListToString).collect(Collectors.joining());
    }

    @Override
    public String getPlayersByGoals(int goals) {
        return "";
    }

    @Override
    public String getPlayersByAssists(int assists) {
        return " ";
    }

    @Override
    public String getPlayersByYellowCards() {
        return " ";
    }

    @Override
    public String getPlayersByGoalsInYear(int goals, int year) {
        return " ";
    }

    @Override
    public Player updatePlayer(PLayerRequest player, long id) {
        Player player1 = playerRepository.findById(id).orElse(null);
        if (player1 != null) {
            modelMapper.map(player, player1);
            return playerRepository.save(player1);
        }
        return null;
    }
    @Override
    public String convertClubListToString(Player clubs) {
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
