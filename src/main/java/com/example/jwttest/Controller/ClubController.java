package com.example.jwttest.Controller;


import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.DTO.Club.ClubRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Service.ClubService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@AllArgsConstructor
public class ClubController {
    private ClubService clubService;

    @GetMapping("/")
    public ResponseEntity<String> getClub(HttpServletRequest request) {
        System.out.println(request.getHeader("bearer"));
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }
    @GetMapping("/hot")
    public ResponseEntity<List<ClubDTO>> getHotestClub(){
        return ResponseEntity.ok(clubService.hotestClubs());
    }
    @PostMapping(" ")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addClub(@RequestBody ClubRequest request) {
        clubService.saveClub(request);
        return ResponseEntity.ok("club added");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateClub(@PathVariable Long id, @RequestBody ClubRequest request) {
        clubService.updateClub(id, request);
        return ResponseEntity.ok("club updated");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok("club deleted");
    }

}