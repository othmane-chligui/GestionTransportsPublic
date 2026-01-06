package org.example.servicelignes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneRespDto {
    private Long id;
    private String nom;
    private String type;
}
