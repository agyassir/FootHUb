package com.example.jwttest.Service;

import com.example.jwttest.DTO.Club.ClubRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Repository.ClubRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final ModelMapper modelMapper;

    public ClubService(ClubRepository clubRepository, ModelMapper modelMapper) {
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;
    }

    public String getAllClubs() {
        return clubRepository.findAll().stream()
                .map(this::convertClubListToString).collect(Collectors.joining());
    }

    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElse(null);
    }

    public Club saveClub(ClubRequest club) {
        Club clubEntity = modelMapper.map(club, Club.class);
        return clubRepository.save(clubEntity);
    }

    public Club updateClub(Long id, ClubRequest club) {
        Club existingClub = clubRepository.findById(id).orElse(null);
        if (existingClub != null) {
            modelMapper.map(club, existingClub);
            System.out.println(existingClub);
            return clubRepository.save(existingClub);
        }
        return null;
        }

    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    public String hotestClubs(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        try {
            List<Club> games = clubRepository.findTop6ByOrderByPopularityScoreDesc();
            return objectMapper.writeValueAsString(games); // Converts the list to a JSON array
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert games to JSON", e);
        }
    }


    public String convertClubListToString(Club clubs) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        try {
            return objectMapper.writeValueAsString(clubs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert clubs to JSON", e);
        }
}
}
