package org.example.servicestations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationRespDto {
    private Long id;
    private String nom;
    private String adresse;
    private String nomLigne;
}
