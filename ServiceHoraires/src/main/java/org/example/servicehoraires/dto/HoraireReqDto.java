package org.example.servicehoraires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraireReqDto {
    private Long ligneId;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
}
