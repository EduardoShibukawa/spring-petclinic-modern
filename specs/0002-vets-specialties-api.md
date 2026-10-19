# SPEC 0002: Especialidades Veterinárias e Catálogo de Tipos de Pets

## 1. Visão Geral
Esta especificação define os novos contratos REST e consultas de persistência para apoiar o agendamento de consultas com base na especialidade médica dos veterinários e na validação dos tipos de pets atendidos.

## 2. Contratos REST

### 2.1 Especialidades dos Veterinários
- **Endpoint:** `GET /api/vets/specialties`
- **Response:** `200 OK`
```json
[
  { "id": 1, "name": "radiology" },
  { "id": 2, "name": "surgery" },
  { "id": 3, "name": "dentistry" }
]
```

### 2.2 Filtro de Veterinários por Especialidade
- **Endpoint:** `GET /api/vets?specialty={name}`
- **Parâmetro:** `specialty` (opcional, insensível a maiúsculas/minúsculas).
- **Comportamento:** Se informado, retorna apenas veterinários associados à especialidade correspondente. Se vazio/nulo, retorna todos.

### 2.3 Tipos de Pets
- **Endpoint:** `GET /api/pets/types`
- **Response:** `200 OK`
```json
[
  { "id": 1, "name": "cat" },
  { "id": 2, "name": "dog" },
  { "id": 3, "name": "lizard" }
]
```

## 3. Tarefas Atômicas Planejadas
- **Task 01**: `SpecialtyRepository` + `SpecialtyResponse` Record + Endpoint `GET /api/vets/specialties`.
- **Task 02**: Query `findBySpecialtiesNameIgnoreCase` no `VetRepository` + query param no `VetController`.
- **Task 03**: `PetTypeResponse` Record + `PetTypeController` com `GET /api/pets/types`.

## 4. Testes Rápidos POJO (Sub-segundo)
Todas as tarefas devem implementar testes unitários puros com Mockito ou JUnit simples, garantindo tempo de execução inferior a 250ms, evitando o custo de bootstrap do Spring Context durante o ciclo autônomo.
