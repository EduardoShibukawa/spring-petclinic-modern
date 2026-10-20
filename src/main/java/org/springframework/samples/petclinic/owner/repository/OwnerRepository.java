package org.springframework.samples.petclinic.owner.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets")
    List<Owner> findAllWithPets();

    @Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets WHERE LOWER(o.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Owner> findByLastNameContainingIgnoreCase(@Param("lastName") String lastName);
}
