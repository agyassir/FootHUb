package com.example.jwttest.ClubService;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.example.jwttest.DTO.Club.ClubDTO;
import com.example.jwttest.Entity.Club;
import com.example.jwttest.Repository.ClubRepository;
import com.example.jwttest.Service.ClubService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClubServiceTest {

    @Mock
    private ClubRepository clubRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClubService clubService;

    private Club club1;
    private Club club2;
    private ClubDTO clubDTO1;
    private ClubDTO clubDTO2;

    @BeforeEach
    void setUp() {

        club1 = new Club(1L, "FC Barcelona", 95);
        club2 = new Club(2L, "Real Madrid", 93);


        clubDTO1 = new ClubDTO(1L, "FC Barcelona", 95);
        clubDTO2 = new ClubDTO(2L, "Real Madrid", 93);
    }

    @Test
    void testHotestClubs() {

        when(clubRepository.findTop6ByOrderByPopularityScoreDesc()).thenReturn(Arrays.asList(club1, club2));


        when(modelMapper.map(club1, ClubDTO.class)).thenReturn(clubDTO1);
        when(modelMapper.map(club2, ClubDTO.class)).thenReturn(clubDTO2);


        List<ClubDTO> result = clubService.hotestClubs();


        verify(clubRepository, times(1)).findTop6ByOrderByPopularityScoreDesc();
        verify(modelMapper, times(2)).map(any(Club.class), eq(ClubDTO.class));


        assertThat(result).isNotNull().hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("FC Barcelona");
        assertThat(result.get(1).getName()).isEqualTo("Real Madrid");
    }

    @Test
    void testGetClubById_WhenClubExists() {
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club1));
        when(modelMapper.map(club1, ClubDTO.class)).thenReturn(clubDTO1);

        ClubDTO result = clubService.getClubById(1L);


        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("FC Barcelona");


        verify(clubRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(club1, ClubDTO.class);
    }




}

