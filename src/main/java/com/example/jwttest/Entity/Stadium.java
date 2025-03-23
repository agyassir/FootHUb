package com.example.jwttest.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date DateOfConstruction;
    private int capacity;
    @ManyToOne
    private Location location;
}
