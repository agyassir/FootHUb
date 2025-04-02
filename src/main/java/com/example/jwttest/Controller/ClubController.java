package com.example.jwttest.Controller;


import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.DTO.Club.ClubRequest;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Service.ClubService;
import com.example.jwttest.Service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hibernate.type.descriptor.java.JdbcDateJavaType.DATE_FORMAT;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clubs")
@AllArgsConstructor
public class ClubController {
    private ClubService clubService;
    private FileStorageService fileStorageService;

    @GetMapping()
    public ResponseEntity<Page<ClubDTO>> getClub( @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        Page<ClubDTO> clubsPage = clubService.getAllClubs(pageable);
        return ResponseEntity.ok(clubService.getAllClubs(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @GetMapping("/hma9")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Access granted - User has ROLE_ADMIN");
    }
    @GetMapping("/hot")
    public ResponseEntity<List<ClubDTO>> getHotestClub(){
        return ResponseEntity.ok(clubService.hotestClubs());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClubDTO> createClub(@RequestBody @Valid ClubRequest clubDTO){
        System.out.println(clubDTO);
        ClubDTO savedClub = clubService.saveClub(clubDTO);
        return ResponseEntity.ok(savedClub);
    }

    @PostMapping("/test")
    public ResponseEntity<String> testPOstClub(@RequestBody @Valid String clubDTO){
        System.out.println(clubDTO);
//        ClubDTO savedClub = clubService.saveClub(clubDTO);
        return ResponseEntity.ok(clubDTO);
    }


    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
        public ResponseEntity<String> uploadFile(
            @PathVariable long id,
            @RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println("Received content type: " + contentType);

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty!");
        }
        String fileName = fileStorageService.storeFile(file);
        ClubDTO club = clubService.getClubById(id);
        club.setImage(fileName);
        clubService.updateClub(id,club);
        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> updateClub(@PathVariable Long id, @RequestBody ClubDTO request) {
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