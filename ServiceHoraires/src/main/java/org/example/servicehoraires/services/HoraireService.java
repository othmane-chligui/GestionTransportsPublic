package org.example.servicehoraires.services;

import org.example.servicehoraires.dto.HoraireReqDto;
import org.example.servicehoraires.dto.HoraireRespDto;

import java.util.List;

public interface HoraireService {
    List<HoraireRespDto> findAll();

    HoraireRespDto findById(Long id);

    List<HoraireRespDto> findByLigneId(Long ligneId);

    HoraireRespDto save(HoraireReqDto horaireReqDto);

    HoraireRespDto update(Long id, HoraireReqDto horaireReqDto);

    void deleteById(Long id);

    void deleteByLigneId(Long ligneId);
}
