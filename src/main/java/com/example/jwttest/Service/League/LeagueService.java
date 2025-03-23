package com.example.jwttest.Service.League;


import com.example.jwttest.Entity.Game;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Repository.LeagueRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LeagueService {

    private LeagueRepository leagueRepository;

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
