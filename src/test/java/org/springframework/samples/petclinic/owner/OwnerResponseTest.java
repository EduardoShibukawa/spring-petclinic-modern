package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.dto.OwnerResponse;
import org.springframework.samples.petclinic.owner.model.Owner;
import org.springframework.samples.petclinic.owner.model.Pet;
import org.springframework.samples.petclinic.owner.model.PetType;

class OwnerResponseTest {

    @Test
    void mapsOwnerFieldsCorrectly() {
        Owner owner = new Owner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023");
        owner.setId(1L);

        Pet pet = new Pet("Leo", LocalDate.of(2020, 9, 7), new PetType("cat"));
        pet.setId(10L);
        owner.addPet(pet);

        OwnerResponse response = OwnerResponse.from(owner);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.firstName()).isEqualTo("George");
        assertThat(response.lastName()).isEqualTo("Franklin");
        assertThat(response.address()).isEqualTo("110 W. Liberty St.");
        assertThat(response.city()).isEqualTo("Madison");
        assertThat(response.telephone()).isEqualTo("6085551023");
        assertThat(response.pets()).hasSize(1);
        assertThat(response.pets().get(0).name()).isEqualTo("Leo");
    }

    @Test
    void handlesOwnerWithoutPets() {
        Owner owner = new Owner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749");
        owner.setId(2L);

        OwnerResponse response = OwnerResponse.from(owner);

        assertThat(response.id()).isEqualTo(2L);
        assertThat(response.pets()).isNotNull().isEmpty();
    }
}
