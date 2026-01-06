package org.example.servicelignes.web;

import org.example.servicelignes.dto.LigneReqDto;
import org.example.servicelignes.dto.LigneRespDto;
import org.example.servicelignes.services.LigneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lignes")
public class LigneController {

    @Autowired
    private LigneService ligneService;

    @GetMapping
    public ResponseEntity<List<LigneRespDto>> getAllLignes() {
        List<LigneRespDto> lignes = ligneService.findAll();
        return ResponseEntity.ok(lignes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LigneRespDto> getLigneById(@PathVariable Long id) {
        LigneRespDto ligne = ligneService.findById(id);
        return ResponseEntity.ok(ligne);
    }

    @PostMapping
    public ResponseEntity<LigneRespDto> createLigne(@RequestBody LigneReqDto ligneReqDto) {
        LigneRespDto createdLigne = ligneService.save(ligneReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLigne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LigneRespDto> updateLigne(@PathVariable Long id, @RequestBody LigneReqDto ligneReqDto) {
        LigneRespDto updatedLigne = ligneService.update(id, ligneReqDto);
        return ResponseEntity.ok(updatedLigne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLigne(@PathVariable Long id) {
        ligneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
