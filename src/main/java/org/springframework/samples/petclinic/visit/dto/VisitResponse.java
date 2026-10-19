package org.springframework.samples.petclinic.visit.dto;

import java.time.LocalDate;
import org.springframework.samples.petclinic.visit.model.Visit;

public record VisitResponse(
    Long id,
    Long petId,
    LocalDate visitDate,
    String description
) {
    public static VisitResponse from(Visit visit) {
        return new VisitResponse(visit.getId(), visit.getPetId(), visit.getVisitDate(), visit.getDescription());
    }
}
