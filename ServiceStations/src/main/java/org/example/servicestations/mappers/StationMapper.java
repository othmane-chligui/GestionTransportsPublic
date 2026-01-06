package org.example.servicestations.mappers;

import org.example.servicestations.dto.StationReqDto;
import org.example.servicestations.dto.StationRespDto;
import org.example.servicestations.entities.Station;
import org.springframework.stereotype.Component;

@Component
public class StationMapper {
    
    public Station toEntity(StationReqDto dto) {
        if (dto == null) {
            return null;
        }
        Station station = new Station();
        station.setNom(dto.getNom());
        station.setAdresse(dto.getAdresse());
        station.setLigneId(dto.getLigneId());
        return station;
    }
    
    public StationRespDto toRespDto(Station entity) {
        if (entity == null) {
            return null;
        }
        StationRespDto dto = new StationRespDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setAdresse(entity.getAdresse());
        return dto;
    }
    
    public void updateEntityFromDto(StationReqDto dto, Station entity) {
        if (dto != null && entity != null) {
            entity.setNom(dto.getNom());
            entity.setAdresse(dto.getAdresse());
            entity.setLigneId(dto.getLigneId());
        }
    }
}
