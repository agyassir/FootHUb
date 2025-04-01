package com.example.jwttest.DTO.Stadium;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class    StadiumDTO {
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    
    @NotNull(message = "Capacity is required")
    @Min(value = 1000, message = "Capacity must be at least 1000")
    private Integer capacity;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    // Additional fields as needed
}