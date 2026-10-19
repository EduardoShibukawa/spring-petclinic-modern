package org.springframework.samples.petclinic.visit.controller;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.repository.PetRepository;
import org.springframework.samples.petclinic.visit.dto.CreateVisitRequest;
import org.springframework.samples.petclinic.visit.dto.VisitResponse;
import org.springframework.samples.petclinic.visit.model.Visit;
import org.springframework.samples.petclinic.visit.repository.VisitRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    private final VisitRepository visitRepository;
    private final PetRepository petRepository;

    public VisitController(VisitRepository visitRepository, PetRepository petRepository) {
        this.visitRepository = visitRepository;
        this.petRepository = petRepository;
    }

    @PostMapping
    public ResponseEntity<VisitResponse> scheduleVisit(
            @PathVariable Long ownerId,
            @PathVariable Long petId,
            @Valid @RequestBody CreateVisitRequest request) {

        petRepository.findById(petId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet com id " + petId + " não encontrado"));

        Visit visit = new Visit(petId, request.visitDate(), request.description());
        Visit saved = visitRepository.save(visit);

        return ResponseEntity.created(URI.create("/api/owners/" + ownerId + "/pets/" + petId + "/visits/" + saved.getId()))
            .body(VisitResponse.from(saved));
    }

    @GetMapping
    public ResponseEntity<List<VisitResponse>> getVisits(
            @PathVariable Long ownerId,
            @PathVariable Long petId) {

        List<VisitResponse> visits = visitRepository.findByPetId(petId)
            .stream()
            .map(VisitResponse::from)
            .toList();

        return ResponseEntity.ok(visits);
    }
}
