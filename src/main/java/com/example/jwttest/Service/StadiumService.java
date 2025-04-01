package com.example.jwttest.Service;

import com.example.jwttest.DTO.Stadium.StadiumDTO;
import com.example.jwttest.Entity.Stadium;
import com.example.jwttest.Repository.StadiumRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StadiumService {
    
    private final StadiumRepository stadiumRepository;
    private final ModelMapper modelMapper;



    public List<StadiumDTO> getAllStadiums() {
        return stadiumRepository.findAll().stream().map((element) -> modelMapper.map(element, StadiumDTO.class)).collect(Collectors.toList());
    }

    public StadiumDTO getStadiumById(Long id) {
        return stadiumRepository.findById(id).map((element) -> modelMapper.map(element, StadiumDTO.class)).orElseThrow(null);
    }

    public StadiumDTO createStadium(StadiumDTO stadiumDTO) {
        Stadium stadium = modelMapper.map(stadiumDTO, Stadium.class);
        return modelMapper.map(stadiumRepository.save(stadium), StadiumDTO.class);
    }

    public StadiumDTO updateStadium(Long id, StadiumDTO stadiumDTO) {
        Stadium existingStadium = stadiumRepository.findById(id).orElseThrow(null);

        modelMapper.map(stadiumDTO, existingStadium);

        // Ensure ID remains unchanged
        existingStadium.setId(id);
        Stadium updatedStadium = stadiumRepository.save(existingStadium);
        return modelMapper.map(updatedStadium, StadiumDTO.class);
    }

    public void deleteStadium(Long id) {
        stadiumRepository.deleteById(id);
    }

    public List<StadiumDTO> findByCapacityGreaterThan(int capacity) {
        return stadiumRepository.findByCapacityGreaterThan(capacity).stream().map((element) -> modelMapper.map(element, StadiumDTO.class)).collect(Collectors.toList());
    }
}