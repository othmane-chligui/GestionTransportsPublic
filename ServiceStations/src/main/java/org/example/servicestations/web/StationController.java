package org.example.servicestations.web;

import org.example.servicestations.dto.StationReqDto;
import org.example.servicestations.dto.StationRespDto;
import org.example.servicestations.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {
    
    @Autowired
    private StationService stationService;
    
    @GetMapping
    public ResponseEntity<List<StationRespDto>> getAllStations() {
        List<StationRespDto> stations = stationService.findAll();
        return ResponseEntity.ok(stations);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<StationRespDto> getStationById(@PathVariable Long id) {
        StationRespDto station = stationService.findById(id);
        return ResponseEntity.ok(station);
    }
    
    @GetMapping("/ligne/{ligneId}")
    public ResponseEntity<List<StationRespDto>> getStationsByLigneId(@PathVariable Long ligneId) {
        List<StationRespDto> stations = stationService.findByLigneId(ligneId);
        return ResponseEntity.ok(stations);
    }
    
    @PostMapping
    public ResponseEntity<StationRespDto> createStation(@RequestBody StationReqDto stationReqDto) {
        StationRespDto createdStation = stationService.save(stationReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStation);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StationRespDto> updateStation(@PathVariable Long id, @RequestBody StationReqDto stationReqDto) {
        StationRespDto updatedStation = stationService.update(id, stationReqDto);
        return ResponseEntity.ok(updatedStation);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/ligne/{ligneId}")
    public ResponseEntity<Void> deleteStationsByLigneId(@PathVariable Long ligneId) {
        stationService.deleteByLigneId(ligneId);
        return ResponseEntity.noContent().build();
    }
}
