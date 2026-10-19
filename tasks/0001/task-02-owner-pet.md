# TASK 02: Módulo Owner e Pet com Java 21 Records

- **ID**: `task-02-owner-pet`
- **Target Files**: `src/main/java/org/springframework/samples/petclinic/owner/...`
- **Fast Test Command**: `mvn test -Dtest=OwnerControllerTest`
- **Feature Flag**: `PETCLINIC_FEATURE_VIRTUAL_THREADS=1`

---

## 1. Objetivo Cirúrgico
Implementar o módulo vertical de donos e animais de estimação utilizando **Java 21 Records** para contratos de DTOs, Bean Validation para integridade e Spring Data JPA para persistência em H2.

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-2.1**: DTOs `CreateOwnerRequest` e `PetRequest` implementados como Java Records com anotações `@NotBlank`.
- [ ] **AC-2.2**: `OwnerController` expõe `POST /api/owners` retornando HTTP 201 com `OwnerResponse`.
- [ ] **AC-2.3**: `OwnerController` expõe `POST /api/owners/{ownerId}/pets` associando o pet ao dono correspondente e retornando HTTP 201.
- [ ] **AC-2.4**: Fast Test `mvn test -Dtest=OwnerControllerTest` passa 100%.
