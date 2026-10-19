package org.springframework.samples.petclinic.owner.dto;

import java.util.List;
import org.springframework.samples.petclinic.owner.model.Owner;

public record OwnerResponse(
    Long id,
    String firstName,
    String lastName,
    String address,
    String city,
    String telephone,
    List<PetResponse> pets
) {
    public static OwnerResponse from(Owner owner) {
        List<PetResponse> petDtos = owner.getPets() != null
            ? owner.getPets().stream().map(PetResponse::from).toList()
            : List.of();

        return new OwnerResponse(
            owner.getId(),
            owner.getFirstName(),
            owner.getLastName(),
            owner.getAddress(),
            owner.getCity(),
            owner.getTelephone(),
            petDtos
        );
    }
}
