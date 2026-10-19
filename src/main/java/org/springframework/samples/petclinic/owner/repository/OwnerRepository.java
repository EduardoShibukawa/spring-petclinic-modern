package org.springframework.samples.petclinic.owner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.owner.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
