package org.example.servicehoraires.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "horaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ligne_id", nullable = false)
    private Long ligneId;

    @Column(name = "heure_depart", nullable = false)
    private LocalTime heureDepart;

    @Column(name = "heure_arrivee", nullable = false)
    private LocalTime heureArrivee;
}
