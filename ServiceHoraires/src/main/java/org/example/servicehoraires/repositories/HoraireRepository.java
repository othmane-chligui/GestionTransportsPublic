package org.example.servicehoraires.repositories;

import org.example.servicehoraires.entities.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Long> {
    List<Horaire> findByLigneId(Long ligneId);
}
