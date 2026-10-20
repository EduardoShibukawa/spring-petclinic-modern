package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.owner.dto.PetTypeResponse;
import org.springframework.samples.petclinic.owner.model.PetType;

class PetTypeResponseTest {

    @Test
    void fromMapsIdAndName() {
        PetType petType = new PetType("dog");
        petType.setId(1L);

        PetTypeResponse response = PetTypeResponse.from(petType);

        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("dog");
    }

    @Test
    void fromHandlesNullId() {
        PetType petType = new PetType("cat");

        PetTypeResponse response = PetTypeResponse.from(petType);

        assertThat(response.id()).isNull();
        assertThat(response.name()).isEqualTo("cat");
    }
}
