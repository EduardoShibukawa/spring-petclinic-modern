# Task 02: Filtro de Veterinários por Especialidade

## Objetivo
Adicionar suporte ao parâmetro opcional de consulta `?specialty={name}` no endpoint `GET /api/vets`.

## Arquivos Afetados
- `src/main/java/org/springframework/samples/petclinic/vet/repository/VetRepository.java`
- `src/main/java/org/springframework/samples/petclinic/vet/controller/VetController.java`
- `src/test/java/org/springframework/samples/petclinic/vet/VetControllerFilterTest.java`

## Critérios de Aceite (AC)
1. No `VetRepository`, adicionar o método:
   `List<Vet> findBySpecialtiesNameIgnoreCase(String specialtyName);`
2. No `VetController.listVets()`, adicionar `@RequestParam(required = false) String specialty`.
3. Se `specialty != null && !specialty.isBlank()`, chamar `vetRepository.findBySpecialtiesNameIgnoreCase(specialty)`. Caso contrário, chamar `vetRepository.findAll()`.
4. Criar teste unitário puro com Mockito `VetControllerFilterTest` verificando que o filtro delega corretamente para o repositório sem carregar o Spring Context.
5. Fast Test Command: `mvn test-compile surefire:test -Dtest=VetControllerFilterTest`
