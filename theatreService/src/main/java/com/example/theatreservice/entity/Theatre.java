package com.example.theatreservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long theatreId;
    private String theatreName;
    private String location;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "theatreId", referencedColumnName = "theatreId")
    private List<Screen>screens;
}
