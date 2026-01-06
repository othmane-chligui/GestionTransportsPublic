package org.example.servicelignes.repositories;

import org.example.servicelignes.entities.Ligne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneRepository extends JpaRepository<Ligne, Long> {
}
