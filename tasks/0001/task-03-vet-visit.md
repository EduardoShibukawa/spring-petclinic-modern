# TASK 03: Módulos Vet e Visit com Agendamento de Consultas

- **ID**: `task-03-vet-visit`
- **Target Files**: `src/main/java/org/springframework/samples/petclinic/visit/...`, `src/main/java/org/springframework/samples/petclinic/vet/...`
- **Fast Test Command**: `mvn test -Dtest=VisitControllerTest`
- **Feature Flag**: `PETCLINIC_FEATURE_VIRTUAL_THREADS=1`

---

## 1. Objetivo Cirúrgico
Implementar o módulo de veterinários e especialidades e o módulo de agendamento de consultas (`visits`) associadas a animais de estimação.

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-3.1**: `VetController` expõe `GET /api/vets` retornando lista de veterinários e suas especialidades.
- [ ] **AC-3.2**: `VisitController` expõe `POST /api/owners/{ownerId}/pets/{petId}/visits` persistindo visita com data e descrição.
- [ ] **AC-3.3**: Fast Test `mvn test -Dtest=VisitControllerTest` passa 100%.
