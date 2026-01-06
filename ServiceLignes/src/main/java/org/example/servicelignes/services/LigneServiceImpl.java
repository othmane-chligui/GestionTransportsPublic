package org.example.servicelignes.services;

import org.example.servicelignes.dto.LigneReqDto;
import org.example.servicelignes.dto.LigneRespDto;
import org.example.servicelignes.entities.Ligne;
import org.example.servicelignes.mappers.LigneMapper;
import org.example.servicelignes.repositories.LigneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LigneServiceImpl implements LigneService {

    @Autowired
    private LigneRepository ligneRepository;

    @Autowired
    private LigneMapper ligneMapper;

    @Autowired
    private org.example.servicelignes.clients.StationClient stationClient;

    @Autowired
    private org.example.servicelignes.clients.HoraireClient horaireClient;

    @Override
    public List<LigneRespDto> findAll() {
        return ligneRepository.findAll()
                .stream()
                .map(ligneMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public LigneRespDto findById(Long id) {
        Ligne ligne = ligneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne not found with id: " + id));
        return ligneMapper.toRespDto(ligne);
    }

    @Override
    public LigneRespDto save(LigneReqDto ligneReqDto) {
        Ligne ligne = ligneMapper.toEntity(ligneReqDto);
        Ligne savedLigne = ligneRepository.save(ligne);
        return ligneMapper.toRespDto(savedLigne);
    }

    @Override
    public LigneRespDto update(Long id, LigneReqDto ligneReqDto) {
        Ligne ligne = ligneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne not found with id: " + id));
        ligneMapper.updateEntityFromDto(ligneReqDto, ligne);
        Ligne updatedLigne = ligneRepository.save(ligne);
        return ligneMapper.toRespDto(updatedLigne);
    }

    @Override
    public void deleteById(Long id) {
        if (!ligneRepository.existsById(id)) {
            throw new RuntimeException("Ligne not found with id: " + id);
        }
        // Cascade delete across microservices
        try {
            stationClient.deleteStationsByLigneId(id);
        } catch (Exception e) {
            // Log error but continue
        }
        try {
            horaireClient.deleteHorairesByLigneId(id);
        } catch (Exception e) {
            // Log error
        }

        ligneRepository.deleteById(id);
    }
}
