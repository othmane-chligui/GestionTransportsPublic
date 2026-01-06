package org.example.servicestations.services;

import org.example.servicestations.dto.StationReqDto;
import org.example.servicestations.dto.StationRespDto;
import org.example.servicestations.entities.Station;
import org.example.servicestations.mappers.StationMapper;
import org.example.servicestations.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationServiceImpl implements StationService {
    
    @Autowired
    private StationRepository stationRepository;
    
    @Autowired
    private StationMapper stationMapper;

    @Autowired
    private org.example.servicestations.clients.LigneClient ligneClient;
    
    @Override
    public List<StationRespDto> findAll() {
        return stationRepository.findAll()
                .stream()
                .map(station -> {
                    StationRespDto dto = stationMapper.toRespDto(station);
                    try {
                        dto.setNomLigne(ligneClient.getLigneById(station.getLigneId()).getNom());
                    } catch (Exception e) {
                        dto.setNomLigne("Inconnue");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public StationRespDto findById(Long id) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
        StationRespDto dto = stationMapper.toRespDto(station);
        try {
            dto.setNomLigne(ligneClient.getLigneById(station.getLigneId()).getNom());
        } catch (Exception e) {
            dto.setNomLigne("Inconnue");
        }
        return dto;
    }
    
    @Override
    public List<StationRespDto> findByLigneId(Long ligneId) {
        String nomLigne;
        try {
            nomLigne = ligneClient.getLigneById(ligneId).getNom();
        } catch (Exception e) {
            nomLigne = "Inconnue";
        }
        
        final String finalNomLigne = nomLigne;
        return stationRepository.findByLigneId(ligneId)
                .stream()
                .map(station -> {
                    StationRespDto dto = stationMapper.toRespDto(station);
                    dto.setNomLigne(finalNomLigne);
                    return dto;
                })
                .collect(Collectors.toList());
    }
    
    @Override
    public StationRespDto save(StationReqDto stationReqDto) {
        Station station = stationMapper.toEntity(stationReqDto);
        Station savedStation = stationRepository.save(station);
        return stationMapper.toRespDto(savedStation);
    }
    
    @Override
    public StationRespDto update(Long id, StationReqDto stationReqDto) {
        Station station = stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with id: " + id));
        stationMapper.updateEntityFromDto(stationReqDto, station);
        Station updatedStation = stationRepository.save(station);
        return stationMapper.toRespDto(updatedStation);
    }
    
    @Override
    public void deleteById(Long id) {
        if (!stationRepository.existsById(id)) {
            throw new RuntimeException("Station not found with id: " + id);
        }
        stationRepository.deleteById(id);
    }

    @Override
    public void deleteByLigneId(Long ligneId) {
        List<Station> stations = stationRepository.findByLigneId(ligneId);
        stationRepository.deleteAll(stations);
    }
}
