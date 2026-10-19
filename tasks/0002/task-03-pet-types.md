# Task 03: Catálogo de Tipos de Pets

## Objetivo
Expor o endpoint de listagem de tipos de animais atendidos (`GET /api/pets/types`).

## Arquivos Afetados
- `src/main/java/org/springframework/samples/petclinic/owner/dto/PetTypeResponse.java`
- `src/main/java/org/springframework/samples/petclinic/owner/controller/PetTypeController.java`
- `src/test/java/org/springframework/samples/petclinic/owner/PetTypeResponseTest.java`

## Critérios de Aceite (AC)
1. Criar Record `PetTypeResponse(Long id, String name)` com método estático `from(PetType petType)`.
2. Criar `PetTypeController` mapeado em `/api/pets/types` com método `@GetMapping` retornando `ResponseEntity<List<PetTypeResponse>>` a partir de `PetTypeRepository.findAll()`.
3. Criar teste unitário puro `PetTypeResponseTest` validando a conversão do Record sem carregar o Spring Context.
4. Fast Test Command: `mvn test-compile surefire:test -Dtest=PetTypeResponseTest`
