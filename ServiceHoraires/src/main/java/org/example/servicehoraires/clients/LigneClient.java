package org.example.servicehoraires.clients;

import lombok.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ServiceLignes")
public interface LigneClient {

    @GetMapping("/lignes/{id}")
    LigneDto getLigneById(@PathVariable("id") Long id);

    @Data
    class LigneDto {
        private Long id;
        private String nom;
        private String type;
    }
}
