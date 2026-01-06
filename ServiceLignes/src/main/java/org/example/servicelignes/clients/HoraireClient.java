package org.example.servicelignes.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ServiceHoraires")
public interface HoraireClient {

    @DeleteMapping("/horaires/ligne/{ligneId}")
    void deleteHorairesByLigneId(@PathVariable("ligneId") Long ligneId);
}
