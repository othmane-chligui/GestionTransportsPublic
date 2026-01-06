package org.example.servicelignes.services;

import org.example.servicelignes.dto.LigneReqDto;
import org.example.servicelignes.dto.LigneRespDto;

import java.util.List;

public interface LigneService {
    List<LigneRespDto> findAll();

    LigneRespDto findById(Long id);

    LigneRespDto save(LigneReqDto ligneReqDto);

    LigneRespDto update(Long id, LigneReqDto ligneReqDto);

    void deleteById(Long id);
}
