package com.example.jwttest.Controller;


import com.example.jwttest.DTO.LeagueStanding.LeagueStandingDTO;
import com.example.jwttest.Service.LeagueStanding.StandingService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/standing")
@Data
@AllArgsConstructor
public class StandingController {

    private final StandingService standingService;

    @GetMapping("/club/{id}")
    public ResponseEntity<List<LeagueStandingDTO>> getStandingbyClub(@PathVariable("id") Long id){
        return ResponseEntity.ok(standingService.getStandingByclubId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeagueStandingDTO> updateStanding(@PathVariable("id") Long id, @RequestBody LeagueStandingDTO leagueStandingDTO){
        return ResponseEntity.ok(standingService.update(id, leagueStandingDTO));
    }

    @PostMapping()
    public ResponseEntity<LeagueStandingDTO> createStanding(@RequestBody LeagueStandingDTO leagueStandingDTO){
        return ResponseEntity.ok(standingService.create(leagueStandingDTO));
    }

}

