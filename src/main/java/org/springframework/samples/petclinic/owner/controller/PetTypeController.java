package org.springframework.samples.petclinic.owner.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.dto.PetTypeResponse;
import org.springframework.samples.petclinic.owner.repository.PetTypeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets/types")
public class PetTypeController {

    private final PetTypeRepository petTypeRepository;

    public PetTypeController(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<PetTypeResponse>> listPetTypes() {
        List<PetTypeResponse> types = petTypeRepository.findAll()
            .stream()
            .map(PetTypeResponse::from)
            .toList();
        return ResponseEntity.ok(types);
    }
}
