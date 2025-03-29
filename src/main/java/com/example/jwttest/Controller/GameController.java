package com.example.jwttest.Controller;



import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.DTO.Game.GameDTO;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Entity.Game;
import com.example.jwttest.Entity.League;
import com.example.jwttest.Service.ClubService;
import com.example.jwttest.Service.Game.GameService;
import com.example.jwttest.Service.League.LeagueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {


    private GameService gameService;
    private ClubService clubService;

    // Get all games
    @GetMapping
    public ResponseEntity<String> getAllGames() {
        String games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    // Get a game by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getGameById(@PathVariable Long id) {
        String game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }



    // Create a new game
    @PostMapping
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        String createdGame = gameService.createGame(game);
        return ResponseEntity.ok(createdGame);
    }

    // Update an existing game
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    // Delete a game by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }

    // Get games by date
    @GetMapping("/by-date")
    public ResponseEntity<?> getGamesByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) {
        try {
            // Parse the date manually
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
            isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            SimpleDateFormat slashFormat = new SimpleDateFormat("yyyy/MM/dd");
            slashFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            Date parsedDate;
            try {
                parsedDate = isoFormat.parse(date); // Try ISO format
            } catch (ParseException e) {
                parsedDate = slashFormat.parse(date); // Try slash format
            }

            List<GameDTO> games = gameService.getGamesByDate(parsedDate);
            return ResponseEntity.ok(games);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use YYYY-MM-DD or YYYY/MM/DD.");
        }
    }

    // Get games by home team ID
    @GetMapping("/home-team/{homeTeamId}")
    public ResponseEntity<String> getGamesByHomeTeam(@PathVariable Long homeTeamId) {
        ClubDTO homeTeam= clubService.getClubById(homeTeamId);
        String games = gameService.getGamesByHomeTeam(homeTeam);
        return ResponseEntity.ok(games);
    }

    // Get games by away team ID
    @GetMapping("/away-team/{awayTeamId}")
    public ResponseEntity<String> getGamesByAwayTeam(@PathVariable Long awayTeamId) {
        ClubDTO awayTeam= clubService.getClubById(awayTeamId);
        String games = gameService.getGamesByAwayTeam(awayTeam);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/hot")
    public ResponseEntity<String> getHotGames(){
        String games = gameService.getHotGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/today")
    public ResponseEntity<String> getTodayGames(){

        return ResponseEntity.ok(gameService.getTodaysGame());
    }

    @GetMapping("/upcomingMatch/{id}")
    public ResponseEntity<GameDTO> getUpcomingMatch(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getUpcomingMatch(id));
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<List<GameDTO>> getGamesByClub(@PathVariable Long id){
        List<GameDTO> games = gameService.getGamesByClub(id);
        return ResponseEntity.ok(games);
    }


}
