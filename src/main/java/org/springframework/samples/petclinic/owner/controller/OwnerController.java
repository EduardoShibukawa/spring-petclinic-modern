package org.springframework.samples.petclinic.owner.controller;

import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.dto.CreateOwnerRequest;
import org.springframework.samples.petclinic.owner.dto.OwnerResponse;
import org.springframework.samples.petclinic.owner.dto.PetRequest;
import org.springframework.samples.petclinic.owner.dto.PetResponse;
import org.springframework.samples.petclinic.owner.model.Owner;
import org.springframework.samples.petclinic.owner.model.Pet;
import org.springframework.samples.petclinic.owner.model.PetType;
import org.springframework.samples.petclinic.owner.repository.OwnerRepository;
import org.springframework.samples.petclinic.owner.repository.PetRepository;
import org.springframework.samples.petclinic.owner.repository.PetTypeRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerController(OwnerRepository ownerRepository, PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @GetMapping
    public ResponseEntity<java.util.List<OwnerResponse>> listOwners(@RequestParam(required = false) String lastName) {
        return null;
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> createOwner(@Valid @RequestBody CreateOwnerRequest request) {
        Owner owner = new Owner(
            request.firstName(),
            request.lastName(),
            request.address(),
            request.city(),
            request.telephone()
        );
        Owner saved = ownerRepository.save(owner);
        return ResponseEntity.created(URI.create("/api/owners/" + saved.getId()))
            .body(OwnerResponse.from(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> getOwner(@PathVariable Long id) {
        Owner owner = ownerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dono com id " + id + " não encontrado"));
        return ResponseEntity.ok(OwnerResponse.from(owner));
    }

    @PostMapping("/{ownerId}/pets")
    public ResponseEntity<PetResponse> addPet(@PathVariable Long ownerId, @Valid @RequestBody PetRequest request) {
        Owner owner = ownerRepository.findById(ownerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dono com id " + ownerId + " não encontrado"));

        PetType type = petTypeRepository.findByNameIgnoreCase(request.type())
            .orElseGet(() -> petTypeRepository.save(new PetType(request.type().toLowerCase())));

        Pet pet = new Pet(request.name(), request.birthDate(), type);
        owner.addPet(pet);
        Pet saved = petRepository.save(pet);

        return ResponseEntity.created(URI.create("/api/owners/" + ownerId + "/pets/" + saved.getId()))
            .body(PetResponse.from(saved));
    }
}
