package org.example.servicehoraires.mappers;

import org.example.servicehoraires.dto.HoraireReqDto;
import org.example.servicehoraires.dto.HoraireRespDto;
import org.example.servicehoraires.entities.Horaire;
import org.springframework.stereotype.Component;

@Component
public class HoraireMapper {

    public Horaire toEntity(HoraireReqDto dto) {
        if (dto == null) {
            return null;
        }
        Horaire horaire = new Horaire();
        horaire.setLigneId(dto.getLigneId());
        horaire.setHeureDepart(dto.getHeureDepart());
        horaire.setHeureArrivee(dto.getHeureArrivee());
        return horaire;
    }

    public HoraireRespDto toRespDto(Horaire entity) {
        if (entity == null) {
            return null;
        }
        HoraireRespDto dto = new HoraireRespDto();
        dto.setId(entity.getId());
        dto.setHeureDepart(entity.getHeureDepart());
        dto.setHeureArrivee(entity.getHeureArrivee());
        return dto;
    }

    public void updateEntityFromDto(HoraireReqDto dto, Horaire entity) {
        if (dto != null && entity != null) {
            entity.setLigneId(dto.getLigneId());
            entity.setHeureDepart(dto.getHeureDepart());
            entity.setHeureArrivee(dto.getHeureArrivee());
        }
    }
}
