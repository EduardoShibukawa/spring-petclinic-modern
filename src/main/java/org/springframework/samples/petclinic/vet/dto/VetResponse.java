package org.springframework.samples.petclinic.vet.dto;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.samples.petclinic.vet.model.Vet;

public record VetResponse(
    Long id,
    String firstName,
    String lastName,
    Set<String> specialties
) {
    public static VetResponse from(Vet vet) {
        Set<String> specs = vet.getSpecialties() != null
            ? vet.getSpecialties().stream().map(s -> s.getName()).collect(Collectors.toSet())
            : Set.of();
        return new VetResponse(vet.getId(), vet.getFirstName(), vet.getLastName(), specs);
    }
}
