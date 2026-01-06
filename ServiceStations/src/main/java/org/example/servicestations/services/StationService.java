package org.example.servicestations.services;

import org.example.servicestations.dto.StationReqDto;
import org.example.servicestations.dto.StationRespDto;

import java.util.List;

public interface StationService {
    List<StationRespDto> findAll();
    StationRespDto findById(Long id);
    List<StationRespDto> findByLigneId(Long ligneId);
    StationRespDto save(StationReqDto stationReqDto);
    StationRespDto update(Long id, StationReqDto stationReqDto);
    void deleteById(Long id);
    void deleteByLigneId(Long ligneId);
}
