package org.springframework.samples.petclinic.owner.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.owner.model.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {
    Optional<PetType> findByNameIgnoreCase(String name);
}
