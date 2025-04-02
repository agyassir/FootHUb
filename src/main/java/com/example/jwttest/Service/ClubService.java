package com.example.jwttest.Service;

import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.DTO.Club.ClubRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Entity.Stadium;
import com.example.jwttest.Repository.ClubRepository;
import com.example.jwttest.Repository.LeagueRepository;
import com.example.jwttest.Repository.StadiumRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ClubService {
    private final ClubRepository clubRepository;
    private final LeagueRepository leagueRepository;
    private final StadiumRepository stadiumRepository;
    private final ModelMapper modelMapper;



    public Page<ClubDTO> getAllClubs(Pageable pageable) {
        Page<Club> clubsPage = clubRepository.findAll(pageable);
        return clubsPage.map(club -> modelMapper.map(club, ClubDTO.class));
    }

    public ClubDTO getClubById(Long id) {
        return modelMapper.map(clubRepository.findById(id).orElse(null), ClubDTO.class);
    }

    public ClubDTO saveClub(ClubRequest clubRequest) {
        ClubDTO club= modelMapper.map(clubRequest,ClubDTO.class);
        Club clubEntity = modelMapper.map(club, Club.class);
        return modelMapper.map(clubRepository.save(clubEntity), ClubDTO.class);
    }

    public Club updateClub(Long id, ClubDTO clubDTO) {
        Club existingClub = clubRepository.findById(id).orElse(null);
        if (existingClub != null) {
            // Update simple fields
            existingClub.setName(clubDTO.getName());
            if (clubDTO.getDateOfEstablishement() != null) {
                existingClub.setDateOfEstablishement(clubDTO.getDateOfEstablishement());
            }
            System.out.println(clubDTO.getDateOfEstablishement());
            existingClub.setOwner(clubDTO.getOwner());
            existingClub.setPopularityScore(clubDTO.getPopularityScore());

            // Handle stadium update properly
            if (clubDTO.getStadiumId() != null) {
                Stadium stadium = stadiumRepository.findById(clubDTO.getStadiumId())
                        .orElseThrow(() -> new RuntimeException("Stadium not found"));
                existingClub.setStadium(stadium);
            }

            // Handle league update properly
            if (clubDTO.getLeagueId() != null) {
                League league = leagueRepository.findById(clubDTO.getLeagueId())
                        .orElseThrow(() -> new RuntimeException("League not found"));
                existingClub.setLeague(league);
            }

            return clubRepository.save(existingClub);
        }
        return null;
    }
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    public List<ClubDTO> hotestClubs(){

            List<Club> games = clubRepository.findTop6ByOrderByPopularityScoreDesc();
            return games.stream().map((element) -> modelMapper.map(element, ClubDTO.class)).collect(Collectors.toList());// Converts the list to a JSON array
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
