package org.springframework.samples.petclinic.vet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.vet.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {

    List<Vet> findBySpecialtiesNameIgnoreCase(String specialtyName);
}
