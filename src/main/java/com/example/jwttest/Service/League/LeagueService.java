package com.example.jwttest.Service.League;


import com.example.jwttest.DTO.LeagueStanding.LeagueStandingDTO;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Entity.LeagueSeason;
import com.example.jwttest.Entity.LeagueStanding;
import com.example.jwttest.Repository.LeagueRepository;
import com.example.jwttest.Repository.LeagueSeasonRepository;
import com.example.jwttest.Repository.LeagueStandingRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeagueService {

    private LeagueRepository leagueRepository;
    private LeagueStandingRepository leagueStandingRepository;
    private LeagueSeasonRepository leagueSeasonRepository;
    private ModelMapper modelMapper;


    public String findAll(){
        return convertToArrayJson(leagueRepository.findAll());
    }

    public String convertGameListToString(League clubs) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        try {
            return objectMapper.writeValueAsString(clubs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert clubs to JSON", e);
        }
    }

    public List<LeagueStandingDTO> getStandingByClub(Long id) {
        // 1. Find league with proper exception handling
        League club = leagueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("League not found with id: " + id));


        LeagueSeason season = leagueSeasonRepository.findBySeasonAndLeague("24-25", club)
                .orElseThrow(() -> new EntityNotFoundException("Season '24-25' not found for league: " + id));


        return leagueStandingRepository.findBySeason(season.getId())
                .stream()
                .map(element -> modelMapper.map(element, LeagueStandingDTO.class))
                .collect(Collectors.toList());
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
