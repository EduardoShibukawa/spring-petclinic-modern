package org.springframework.samples.petclinic.owner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.controller.OwnerController;
import org.springframework.samples.petclinic.owner.dto.OwnerResponse;
import org.springframework.samples.petclinic.owner.model.Owner;
import org.springframework.samples.petclinic.owner.repository.OwnerRepository;
import org.springframework.samples.petclinic.owner.repository.PetRepository;
import org.springframework.samples.petclinic.owner.repository.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerControllerFilterTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetTypeRepository petTypeRepository;

    @InjectMocks
    private OwnerController ownerController;

    @Test
    void listOwnersWithoutParamReturnsAllOwners() {
        Owner owner = new Owner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023");
        owner.setId(1L);
        when(ownerRepository.findAllWithPets()).thenReturn(List.of(owner));

        ResponseEntity<List<OwnerResponse>> response = ownerController.listOwners(null);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).firstName()).isEqualTo("George");
        verify(ownerRepository).findAllWithPets();
    }

    @Test
    void listOwnersWithLastNameFilterDelegatesToRepository() {
        Owner owner = new Owner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749");
        owner.setId(2L);
        when(ownerRepository.findByLastNameContainingIgnoreCase("Davis")).thenReturn(List.of(owner));

        ResponseEntity<List<OwnerResponse>> response = ownerController.listOwners("Davis");

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).lastName()).isEqualTo("Davis");
        verify(ownerRepository).findByLastNameContainingIgnoreCase("Davis");
    }

    @Test
    void listOwnersReturnsEmptyListWhenNoMatches() {
        when(ownerRepository.findByLastNameContainingIgnoreCase("Unknown")).thenReturn(List.of());

        ResponseEntity<List<OwnerResponse>> response = ownerController.listOwners("Unknown");

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull().isEmpty();
    }
}
