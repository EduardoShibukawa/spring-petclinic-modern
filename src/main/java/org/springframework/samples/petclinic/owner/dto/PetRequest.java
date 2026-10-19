package org.springframework.samples.petclinic.owner.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record PetRequest(
    @NotBlank(message = "O nome do pet é obrigatório")
    String name,
    LocalDate birthDate,
    @NotBlank(message = "O tipo do pet é obrigatório")
    String type
) {}
