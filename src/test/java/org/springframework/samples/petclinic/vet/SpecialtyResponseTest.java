package org.springframework.samples.petclinic.vet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.vet.dto.SpecialtyResponse;
import org.springframework.samples.petclinic.vet.model.Specialty;

class SpecialtyResponseTest {

    @Test
    void fromSpecialtyMapsIdAndName() {
        Specialty specialty = new Specialty("Dentistry");
        specialty.setId(42L);

        SpecialtyResponse response = SpecialtyResponse.from(specialty);

        assertNotNull(response);
        assertEquals(42L, response.id());
        assertEquals("Dentistry", response.name());
    }

    @Test
    void fromSpecialtyWithoutIdMapsNullId() {
        Specialty specialty = new Specialty("Radiology");

        SpecialtyResponse response = SpecialtyResponse.from(specialty);

        assertNotNull(response);
        assertEquals(null, response.id());
        assertEquals("Radiology", response.name());
    }
}
