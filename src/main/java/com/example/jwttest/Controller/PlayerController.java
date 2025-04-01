package com.example.jwttest.Controller;


import com.example.jwttest.DTO.Player.PLayerRequest;
import com.example.jwttest.DTO.Player.PlayerDTO;
import com.example.jwttest.DTO.Player.PlayerTransferDTO;
import com.example.jwttest.Entity.Player;
import com.example.jwttest.Entity.ENUM.Position;
import com.example.jwttest.Service.Player.PlayerService;
import com.example.jwttest.Service.PlayerTransfer.PlayerTrasferService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerTrasferService playerTrasferService;
    @GetMapping()
    public ResponseEntity<String> getAll(){

        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/trending")
    public ResponseEntity<PlayerDTO> getTrending(){

        return ResponseEntity.ok(playerService.getTrendingPlayer());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable long id){
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }
    @GetMapping("/age/{age}")
    public ResponseEntity<String> getByAge(@PathVariable int age){
        return ResponseEntity.ok(playerService.getPlayersByAge(age));
    }

        @GetMapping("/transfer/{id}")
    public ResponseEntity<List<PlayerTransferDTO>> getPLayerDTO(@PathVariable long id){
        return ResponseEntity.ok(playerTrasferService.getPlayerTransfers(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getPlayersByName(@PathVariable String name) {
        String players = playerService.getPlayersByName(name);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by club ID
    @GetMapping("/club/{id}")
    public ResponseEntity<String> getPlayersByClub(@PathVariable long id) {
        String players = playerService.getPlayersByClub(id);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by position
    @GetMapping("/position/{position}")
    public ResponseEntity<String> getPlayersByPosition(@PathVariable Position position) {
        String players = playerService.getPlayersByPosition(position);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by nationality
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<String> getPlayersByNationality(@PathVariable String nationality) {
        String players = playerService.getPlayersByNationality(nationality);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by market value range
    @GetMapping("/market-value")
    public ResponseEntity<String> getPlayersByMarketValue(
            @RequestParam double minMarketValue,
            @RequestParam double maxMarketValue) {
        String players = playerService.getPlayersByMarketValue(minMarketValue, maxMarketValue);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by contract expiration date
    @GetMapping("/contract-until")
    public ResponseEntity<String> getPlayersByContractUntil(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date contractUntil) {
        String players = playerService.getPlayersByContractUntil(contractUntil);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by number of goals
    @GetMapping("/goals/{goals}")
    public ResponseEntity<String> getPlayersByGoals(@PathVariable int goals) {
        String players = playerService.getPlayersByGoals(goals);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by number of assists
    @GetMapping("/assists/{assists}")
    public ResponseEntity<String> getPlayersByAssists(@PathVariable int assists) {
        String players = playerService.getPlayersByAssists(assists);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by number of yellow cards
    @GetMapping("/yellow-cards")
    public ResponseEntity<String> getPlayersByYellowCards() {
        String players = playerService.getPlayersByYellowCards();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Get players by number of goals in a specific year
    @GetMapping("/goals-in-year")
    public ResponseEntity<String> getPlayersByGoalsInYear(
            @RequestParam int goals,
            @RequestParam int year) {
        String players = playerService.getPlayersByGoalsInYear(goals, year);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Save a new player
    @PostMapping
    public ResponseEntity<Player> savePlayer(@RequestBody PLayerRequest player) {
        Player savedPlayer = playerService.savePlayer(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.CREATED);
    }

    // Update an existing player
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(
            @RequestBody PLayerRequest playerRequest,
            @PathVariable long id) {
        Player updatedPlayer = playerService.updatePlayer(playerRequest, id);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    // Delete a player by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
