# SPEC 0001: Modern Spring PetClinic REST API

- **Status**: APPROVED
- **Reviewer**: Human-in-the-Loop (Senior Architect)
- **Decisões Aprovadas**:
  1. **Arquitetura**: Modular Monolith (Package-by-Feature: `vet`, `owner`, `visit`).
  2. **Java 21 Idioms**: Java Records para todos os DTOs, validações via Jakarta Bean Validation.
  3. **Tratamento de Erros**: RFC 7807 (`ProblemDetail`) nativo do Spring 6/Boot 3.
  4. **Throughput**: Project Loom Virtual Threads (`spring.threads.virtual.enabled=true`).
  5. **Persistência**: Spring Data JPA com banco H2 em memória para testes < 2s.

---

## 1. Módulos & Contratos de Domínio

### 1.1 Módulo `vet`
- `Vet`: id (Long), firstName (String), lastName (String), specialties (Set<Specialty>).
- `Specialty`: id (Long), name (String).
- Endpoints: `GET /api/vets`

### 1.2 Módulo `owner` & `pet`
- `Owner`: id (Long), firstName (String), lastName (String), address (String), city (String), telephone (String), pets (List<Pet>).
- `Pet`: id (Long), name (String), birthDate (LocalDate), type (PetType), owner (Owner).
- `PetType`: id (Long), name (String).
- Endpoints:
  - `POST /api/owners` (Cria dono via `CreateOwnerRequest`)
  - `GET /api/owners/{id}`
  - `POST /api/owners/{ownerId}/pets` (Cria pet associado)

### 1.3 Módulo `visit`
- `Visit`: id (Long), petId (Long), visitDate (LocalDate), description (String).
- Endpoints:
  - `POST /api/owners/{ownerId}/pets/{petId}/visits` (Cria visita)
  - `GET /api/owners/{ownerId}/pets/{petId}/visits`

---

## 2. Decomposição de Tarefas Atômicas (`tasks/0001/`)

1. **Task 01 (`task-01-pom-domain.md`)**: Configuração de `pom.xml` (Spring Boot 3.4, Java 21, H2, Validation) e entidades base.
2. **Task 02 (`task-02-owner-pet.md`)**: Módulo de donos e pets (Owner, Pet, DTOs com Java Records, repositórios e endpoints).
3. **Task 03 (`task-03-vet-visit.md`)**: Módulo de veterinários e visitas (Vet, Specialty, Visit, DTOs e endpoints).
4. **Task 04 (`task-04-error-handling.md`)**: Tratamento global de erros RFC 7807 (`ProblemDetail`) e OpenAPI.
