package com.example.jwttest.Controller;



import com.example.jwttest.DTO.Stadium.StadiumDTO;
import com.example.jwttest.Service.StadiumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stadium")
public class StadiumController {

    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<List<StadiumDTO>> getAllStadiums() {
        return ResponseEntity.ok(stadiumService.getAllStadiums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StadiumDTO> getStadiumById(@PathVariable Long id) {
        return ResponseEntity.ok(stadiumService.getStadiumById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<StadiumDTO> createStadium(@RequestBody StadiumDTO stadiumDTO) {
        return ResponseEntity.ok(stadiumService.createStadium(stadiumDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StadiumDTO> updateStadium(
            @PathVariable Long id,
            @RequestBody StadiumDTO stadiumDTO) {
        return ResponseEntity.ok(stadiumService.updateStadium(id, stadiumDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStadium(@PathVariable Long id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints as needed
    @GetMapping("/capacity-greater-than/{capacity}")
    public ResponseEntity<List<StadiumDTO>> getStadiumsByMinCapacity(
            @PathVariable int capacity) {
        return ResponseEntity.ok(stadiumService.findByCapacityGreaterThan(capacity));
    }
}