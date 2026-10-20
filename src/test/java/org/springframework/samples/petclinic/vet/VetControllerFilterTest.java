package org.springframework.samples.petclinic.vet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vet.controller.VetController;
import org.springframework.samples.petclinic.vet.dto.VetResponse;
import org.springframework.samples.petclinic.vet.model.Specialty;
import org.springframework.samples.petclinic.vet.model.Vet;
import org.springframework.samples.petclinic.vet.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.repository.VetRepository;

@ExtendWith(MockitoExtension.class)
class VetControllerFilterTest {

    @Mock
    private VetRepository vetRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private VetController vetController;

    private Vet vetWithSpecialty(String firstName, String lastName, String specialtyName) {
        Vet vet = new Vet(firstName, lastName);
        vet.setId(1L);
        vet.addSpecialty(new Specialty(specialtyName));
        return vet;
    }

    @Test
    void listVetsWithSpecialtyParamDelegatesToFindBySpecialtiesNameIgnoreCase() {
        Vet vet = vetWithSpecialty("Helen", "Shapiro", "Dentistry");
        when(vetRepository.findBySpecialtiesNameIgnoreCase("Dentistry")).thenReturn(List.of(vet));

        ResponseEntity<List<VetResponse>> response = vetController.listVets("Dentistry");

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0).firstName()).isEqualTo("Helen");
        assertThat(response.getBody().get(0).specialties()).containsExactly("Dentistry");
        verify(vetRepository).findBySpecialtiesNameIgnoreCase("Dentistry");
        verify(vetRepository, never()).findAll();
    }

    @Test
    void listVetsWithoutSpecialtyParamReturnsAllVets() {
        when(vetRepository.findAll()).thenReturn(List.of(
            vetWithSpecialty("Helen", "Shapiro", "Dentistry"),
            vetWithSpecialty("Kim", "Bazeley", "Radiology")
        ));

        ResponseEntity<List<VetResponse>> response = vetController.listVets(null);

        assertThat(response.getBody()).hasSize(2);
        verify(vetRepository).findAll();
        verify(vetRepository, never()).findBySpecialtiesNameIgnoreCase(anyString());
    }

    @Test
    void listVetsWithBlankSpecialtyParamReturnsAllVets() {
        when(vetRepository.findAll()).thenReturn(List.of(
            vetWithSpecialty("Helen", "Shapiro", "Dentistry")
        ));

        ResponseEntity<List<VetResponse>> response = vetController.listVets("   ");

        assertThat(response.getBody()).hasSize(1);
        verify(vetRepository).findAll();
        verify(vetRepository, never()).findBySpecialtiesNameIgnoreCase(anyString());
    }

    @Test
    void listVetsWithNonMatchingSpecialtyReturnsEmptyList() {
        when(vetRepository.findBySpecialtiesNameIgnoreCase("Surgery")).thenReturn(List.of());

        ResponseEntity<List<VetResponse>> response = vetController.listVets("Surgery");

        assertThat(response.getBody()).isEmpty();
        verify(vetRepository).findBySpecialtiesNameIgnoreCase("Surgery");
        verify(vetRepository, never()).findAll();
    }
}
