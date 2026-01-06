package org.example.servicelignes.mappers;

import org.example.servicelignes.dto.LigneReqDto;
import org.example.servicelignes.dto.LigneRespDto;
import org.example.servicelignes.entities.Ligne;
import org.springframework.stereotype.Component;

@Component
public class LigneMapper {

    public Ligne toEntity(LigneReqDto dto) {
        if (dto == null) {
            return null;
        }
        Ligne ligne = new Ligne();
        ligne.setNom(dto.getNom());
        ligne.setType(dto.getType());
        return ligne;
    }

    public LigneRespDto toRespDto(Ligne entity) {
        if (entity == null) {
            return null;
        }
        LigneRespDto dto = new LigneRespDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setType(entity.getType());
        return dto;
    }

    public void updateEntityFromDto(LigneReqDto dto, Ligne entity) {
        if (dto != null && entity != null) {
            entity.setNom(dto.getNom());
            entity.setType(dto.getType());
        }
    }
}
