package org.springframework.samples.petclinic.owner.dto;

import org.springframework.samples.petclinic.owner.model.PetType;

public record PetTypeResponse(Long id, String name) {
    public static PetTypeResponse from(PetType petType) {
        return new PetTypeResponse(petType.getId(), petType.getName());
    }
}
