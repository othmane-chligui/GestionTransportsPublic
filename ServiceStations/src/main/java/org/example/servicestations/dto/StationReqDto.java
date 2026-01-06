package org.example.servicestations.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationReqDto {
    private String nom;
    private String adresse;
    private Long ligneId;
}
