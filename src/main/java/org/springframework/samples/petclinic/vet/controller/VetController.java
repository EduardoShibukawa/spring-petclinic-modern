package org.springframework.samples.petclinic.vet.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vet.dto.SpecialtyResponse;
import org.springframework.samples.petclinic.vet.dto.VetResponse;
import org.springframework.samples.petclinic.vet.repository.SpecialtyRepository;
import org.springframework.samples.petclinic.vet.repository.VetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vets")
public class VetController {

    private final VetRepository vetRepository;
    private final SpecialtyRepository specialtyRepository;

    public VetController(VetRepository vetRepository, SpecialtyRepository specialtyRepository) {
        this.vetRepository = vetRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @GetMapping
    public ResponseEntity<List<VetResponse>> listVets() {
        List<VetResponse> vets = vetRepository.findAll()
            .stream()
            .map(VetResponse::from)
            .toList();
        return ResponseEntity.ok(vets);
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<SpecialtyResponse>> listSpecialties() {
        List<SpecialtyResponse> specialties = specialtyRepository.findAll()
            .stream()
            .map(SpecialtyResponse::from)
            .toList();
        return ResponseEntity.ok(specialties);
    }
}
