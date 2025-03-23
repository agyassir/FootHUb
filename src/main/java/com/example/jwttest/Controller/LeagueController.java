package com.example.jwttest.Controller;


import com.example.jwttest.Entity.League;
import com.example.jwttest.Service.League.LeagueService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
@Data
@AllArgsConstructor
public class LeagueController {
    private LeagueService leagueService;

    @GetMapping()
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok(leagueService.findAll());
    }
}
