# INTENT 0002: Especialidades Veterinárias e Catálogo de Tipos de Pets

## Objetivo de Negócio
Expandir a API REST do Spring PetClinic Moderno para permitir:
1. Consulta e filtragem avançada de Veterinários por Especialidade (`GET /api/vets?specialty=radiology`).
2. Listagem de todas as especialidades disponíveis na clínica (`GET /api/vets/specialties`).
3. Listagem de todos os tipos de animais aceitos (`GET /api/pets/types`).

## Diretrizes de Engenharia e Performance (Harness v2)
- **Java 21 LTS Records**: DTOs imutáveis para requests e responses (`SpecialtyResponse`, `PetTypeResponse`).
- **Arquitetura Modular Monolith**: Manter regras dentro dos seus bounded contexts (`vet` e `owner`).
- **Fast Feedback Unit Testing**: As tarefas atômicas devem utilizar testes unitários em POJO puro com Mockito/JUnit 5 sem subir o `@SpringBootTest` (execução em < 0.2s), reservando o Reality Verifier BDD para o teste de integração final.
- **RFC 7807 ProblemDetail**: Qualquer erro deve retornar o payload padronizado da RFC 7807.
