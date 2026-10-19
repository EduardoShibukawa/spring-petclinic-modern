# Task 01: Especialidades Veterinárias - Repository, DTO e Endpoint

## Objetivo
Implementar o catálogo de especialidades veterinárias (`GET /api/vets/specialties`), expondo as especialidades cadastradas no sistema.

## Arquivos Afetados
- `src/main/java/org/springframework/samples/petclinic/vet/repository/SpecialtyRepository.java`
- `src/main/java/org/springframework/samples/petclinic/vet/dto/SpecialtyResponse.java`
- `src/main/java/org/springframework/samples/petclinic/vet/controller/VetController.java`
- `src/test/java/org/springframework/samples/petclinic/vet/SpecialtyResponseTest.java`

## Critérios de Aceite (AC)
1. Criar interface `SpecialtyRepository` estendendo `JpaRepository<Specialty, Long>`.
2. Criar Record `SpecialtyResponse(Long id, String name)` com método estático `from(Specialty specialty)`.
3. Adicionar método `@GetMapping("/specialties")` no `VetController` injetando `SpecialtyRepository` e retornando `ResponseEntity<List<SpecialtyResponse>>`.
4. Criar teste unitário puro em `SpecialtyResponseTest` validando a conversão de `Specialty` para `SpecialtyResponse` sem carregar o Spring Context.
5. Fast Test Command: `mvn test-compile surefire:test -Dtest=SpecialtyResponseTest`
