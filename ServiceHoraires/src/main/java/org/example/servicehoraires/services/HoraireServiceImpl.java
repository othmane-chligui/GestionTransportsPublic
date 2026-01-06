package org.example.servicehoraires.services;

import org.example.servicehoraires.dto.HoraireReqDto;
import org.example.servicehoraires.dto.HoraireRespDto;
import org.example.servicehoraires.entities.Horaire;
import org.example.servicehoraires.mappers.HoraireMapper;
import org.example.servicehoraires.repositories.HoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HoraireServiceImpl implements HoraireService {

    @Autowired
    private HoraireRepository horaireRepository;

    @Autowired
    private HoraireMapper horaireMapper;

    @Autowired
    private org.example.servicehoraires.clients.LigneClient ligneClient;

    @Override
    public List<HoraireRespDto> findAll() {
        return horaireRepository.findAll()
                .stream()
                .map(horaire -> {
                    HoraireRespDto dto = horaireMapper.toRespDto(horaire);
                    try {
                        dto.setNomLigne(ligneClient.getLigneById(horaire.getLigneId()).getNom());
                    } catch (Exception e) {
                        dto.setNomLigne("Inconnue");
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HoraireRespDto findById(Long id) {
        Horaire horaire = horaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horaire not found with id: " + id));
        HoraireRespDto dto = horaireMapper.toRespDto(horaire);
        try {
            dto.setNomLigne(ligneClient.getLigneById(horaire.getLigneId()).getNom());
        } catch (Exception e) {
            dto.setNomLigne("Inconnue");
        }
        return dto;
    }

    @Override
    public List<HoraireRespDto> findByLigneId(Long ligneId) {
        String nomLigne;
        try {
            nomLigne = ligneClient.getLigneById(ligneId).getNom();
        } catch (Exception e) {
            nomLigne = "Inconnue";
        }

        final String finalNomLigne = nomLigne;
        return horaireRepository.findByLigneId(ligneId)
                .stream()
                .map(horaire -> {
                    HoraireRespDto dto = horaireMapper.toRespDto(horaire);
                    dto.setNomLigne(finalNomLigne);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public HoraireRespDto save(HoraireReqDto horaireReqDto) {
        Horaire horaire = horaireMapper.toEntity(horaireReqDto);
        Horaire savedHoraire = horaireRepository.save(horaire);
        return horaireMapper.toRespDto(savedHoraire);
    }

    @Override
    public HoraireRespDto update(Long id, HoraireReqDto horaireReqDto) {
        Horaire horaire = horaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horaire not found with id: " + id));
        horaireMapper.updateEntityFromDto(horaireReqDto, horaire);
        Horaire updatedHoraire = horaireRepository.save(horaire);
        return horaireMapper.toRespDto(updatedHoraire);
    }

    @Override
    public void deleteById(Long id) {
        if (!horaireRepository.existsById(id)) {
            throw new RuntimeException("Horaire not found with id: " + id);
        }
        horaireRepository.deleteById(id);
    }

    @Override
    public void deleteByLigneId(Long ligneId) {
        List<Horaire> horaires = horaireRepository.findByLigneId(ligneId);
        horaireRepository.deleteAll(horaires);
    }
}
