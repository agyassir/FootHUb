package com.example.jwttest.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Name;
    private Date DateOfEstablishement;
    private String Owner;
    private String Image;
    private int popularityScore;
    @ManyToOne(fetch = FetchType.EAGER) // Use EAGER fetching
    @JoinColumn(name = "stadium_id")
    private Stadium Stadium;
    @OneToMany(mappedBy = "club")
    @JsonBackReference
    private List<Player> players;
    @ManyToOne
    private League league;

}
