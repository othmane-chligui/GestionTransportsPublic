package org.example.servicehoraires.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoraireRespDto {
    private Long id;
    private LocalTime heureDepart;
    private LocalTime heureArrivee;
    private String nomLigne;
}
