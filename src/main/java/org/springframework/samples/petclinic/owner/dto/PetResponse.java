package org.springframework.samples.petclinic.owner.dto;

import java.time.LocalDate;
import org.springframework.samples.petclinic.owner.model.Pet;

public record PetResponse(
    Long id,
    String name,
    LocalDate birthDate,
    String type
) {
    public static PetResponse from(Pet pet) {
        String typeName = pet.getType() != null ? pet.getType().getName() : "unknown";
        return new PetResponse(pet.getId(), pet.getName(), pet.getBirthDate(), typeName);
    }
}
