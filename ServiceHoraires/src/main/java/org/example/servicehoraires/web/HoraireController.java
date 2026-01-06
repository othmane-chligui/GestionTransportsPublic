package org.example.servicehoraires.web;

import org.example.servicehoraires.dto.HoraireReqDto;
import org.example.servicehoraires.dto.HoraireRespDto;
import org.example.servicehoraires.services.HoraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horaires")
public class HoraireController {

    @Autowired
    private HoraireService horaireService;

    @GetMapping
    public ResponseEntity<List<HoraireRespDto>> getAllHoraires() {
        List<HoraireRespDto> horaires = horaireService.findAll();
        return ResponseEntity.ok(horaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoraireRespDto> getHoraireById(@PathVariable Long id) {
        HoraireRespDto horaire = horaireService.findById(id);
        return ResponseEntity.ok(horaire);
    }

    @GetMapping("/ligne/{ligneId}")
    public ResponseEntity<List<HoraireRespDto>> getHorairesByLigneId(@PathVariable Long ligneId) {
        List<HoraireRespDto> horaires = horaireService.findByLigneId(ligneId);
        return ResponseEntity.ok(horaires);
    }

    @PostMapping
    public ResponseEntity<HoraireRespDto> createHoraire(@RequestBody HoraireReqDto horaireReqDto) {
        HoraireRespDto createdHoraire = horaireService.save(horaireReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHoraire);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoraireRespDto> updateHoraire(@PathVariable Long id,
            @RequestBody HoraireReqDto horaireReqDto) {
        HoraireRespDto updatedHoraire = horaireService.update(id, horaireReqDto);
        return ResponseEntity.ok(updatedHoraire);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoraire(@PathVariable Long id) {
        horaireService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ligne/{ligneId}")
    public ResponseEntity<Void> deleteHorairesByLigneId(@PathVariable Long ligneId) {
        horaireService.deleteByLigneId(ligneId);
        return ResponseEntity.noContent().build();
    }
}
