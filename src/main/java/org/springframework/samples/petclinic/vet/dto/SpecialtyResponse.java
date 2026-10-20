package org.springframework.samples.petclinic.vet.dto;

import org.springframework.samples.petclinic.vet.model.Specialty;

public record SpecialtyResponse(Long id, String name) {

    public static SpecialtyResponse from(Specialty specialty) {
        return new SpecialtyResponse(specialty.getId(), specialty.getName());
    }
}
