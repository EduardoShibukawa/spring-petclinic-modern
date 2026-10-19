package org.springframework.samples.petclinic.visit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateVisitRequest(
    @NotNull(message = "A data da visita é obrigatória")
    LocalDate visitDate,

    @NotBlank(message = "A descrição da visita é obrigatória")
    String description
) {}
