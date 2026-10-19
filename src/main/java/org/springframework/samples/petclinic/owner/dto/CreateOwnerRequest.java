package org.springframework.samples.petclinic.owner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateOwnerRequest(
    @NotBlank(message = "O primeiro nome é obrigatório")
    @Size(max = 50, message = "O primeiro nome deve ter no máximo 50 caracteres")
    String firstName,

    @NotBlank(message = "O sobrenome é obrigatório")
    @Size(max = 50, message = "O sobrenome deve ter no máximo 50 caracteres")
    String lastName,

    @NotBlank(message = "O endereço é obrigatório")
    String address,

    @NotBlank(message = "A cidade é obrigatória")
    String city,

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^[0-9]{10,12}$", message = "O telefone deve conter entre 10 e 12 dígitos numéricos")
    String telephone
) {}
