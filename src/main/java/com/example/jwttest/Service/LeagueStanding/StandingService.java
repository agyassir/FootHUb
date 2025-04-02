package com.example.jwttest.Service.LeagueStanding;


import com.example.jwttest.DTO.LeagueSeason.LeagueSeasonDTO;
import com.example.jwttest.DTO.LeagueStanding.LeagueStandingDTO;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Entity.LeagueSeason;
import com.example.jwttest.Entity.LeagueStanding;
import com.example.jwttest.Repository.ClubRepository;
import com.example.jwttest.Repository.LeagueSeasonRepository;
import com.example.jwttest.Repository.LeagueStandingRepository;
import com.example.jwttest.Service.ClubService;
import com.example.jwttest.Service.League.LeagueService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StandingService {

    private final LeagueService leagueService;
    private final ClubRepository clubService;
    private LeagueStandingRepository leagueStandingRepository;
    private final  LeagueSeasonRepository leagueSeasonRepository;
    private final ModelMapper modelMapper;

    public List<LeagueStandingDTO> getStandingByclubId(Long Id) {
        return leagueStandingRepository.findByClubId(Id).stream().map(leagueStanding -> modelMapper.map(leagueStanding, LeagueStandingDTO.class)).toList();
    }

    public LeagueStandingDTO update(long id, LeagueStandingDTO leagueStandingDTO) {
        LeagueStanding leagueStanding = leagueStandingRepository.findById(id).orElseThrow();
        leagueStanding.setStanding(leagueStandingDTO.getStanding());
        leagueStanding.setPoints(leagueStandingDTO.getPoints());
        leagueStanding.setMatch_played(leagueStandingDTO.getMatch_played());
        leagueStanding.setDraw(leagueStandingDTO.getDraw());
        leagueStanding.setWin(leagueStandingDTO.getWin());
        leagueStanding.setLoss(leagueStandingDTO.getLoss());
        return modelMapper.map(leagueStandingRepository.save(leagueStanding), LeagueStandingDTO.class);

    }

    public LeagueStandingDTO create(LeagueStandingDTO leagueStandingDTO){
        League league=clubService.findLeagueByClubId(leagueStandingDTO.getClubId()).orElseThrow(() -> new EntityNotFoundException("there is  no league for club: " + leagueStandingDTO.getClubId()));
        LeagueSeason leagueSeason= leagueSeasonRepository.findBySeasonAndLeague("24-25",league).orElseGet(() -> createNewLeagueSeason("24-25", league));

        leagueStandingDTO.setLeagueSeason(modelMapper.map(leagueSeason, LeagueSeasonDTO.class));


             LeagueStanding result=modelMapper.map(leagueStandingDTO, LeagueStanding.class);
        System.out.println(result);
   return modelMapper.map(leagueStandingRepository.save(result), LeagueStandingDTO.class);
    }

    private LeagueSeason createNewLeagueSeason(String season, League league) {
        LeagueSeason newLeagueSeason = new LeagueSeason();
        newLeagueSeason.setSeason(season);
        newLeagueSeason.setLeague(league);
        return leagueSeasonRepository.save(newLeagueSeason);
    }

}
