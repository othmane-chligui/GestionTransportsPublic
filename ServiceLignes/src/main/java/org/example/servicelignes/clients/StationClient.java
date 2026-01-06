package org.example.servicelignes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ServiceStations")
public interface StationClient {

    @DeleteMapping("/stations/ligne/{ligneId}")
    void deleteStationsByLigneId(@PathVariable("ligneId") Long ligneId);
}
