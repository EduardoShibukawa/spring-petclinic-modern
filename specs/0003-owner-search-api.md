# SPEC 0003: API de Busca e Listagem de Donos de Animais (Owners)

> **Status:** APPROVED & FROZEN  
> **Target Project:** `duh-homelab/spring-petclinic-modern`  
> **Alinhamento:** 100% Canônico do Spring PetClinic Original (REST & Data)

---

## 1. Visão Geral Técnica e Arquitetura

Implementação canônica da API REST de consulta e filtragem de donos de animais de acordo com o padrão oficial do Spring PetClinic REST.

### Componentes Afetados:
1. **Dados Iniciais (`src/main/resources/data.sql`)**:
   - Inserção dos 10 donos canônicos do PetClinic (George Franklin, Betty Davis, Eduardo Rodriquez, etc.).
   - Inserção dos 13 pets canônicos vinculados aos seus respectivos donos e tipos.
2. **Camada de Repositório (`OwnerRepository.java`)**:
   - Busca com `LEFT JOIN FETCH o.pets` para evitar N+1 queries:
     ```java
     @Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets")
     List<Owner> findAllWithPets();

     @Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets WHERE LOWER(o.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
     List<Owner> findByLastNameContainingIgnoreCase(@Param("lastName") String lastName);
     ```
3. **Camada de Exposição (`OwnerController.java`)**:
   - Endpoint canônico `@GetMapping` em `/api/owners`:
     ```java
     @GetMapping
     public ResponseEntity<List<OwnerResponse>> listOwners(
         @RequestParam(required = false) String lastName
     )
     ```
   - Se `lastName` for informado e não vazio, delega para `findByLastNameContainingIgnoreCase`.
   - Se `lastName` for omitido ou vazio, delega para `findAllWithPets` (ou `findAll`).
   - Se nenhum resultado for encontrado, retorna `[]` com HTTP 200.

---

## 2. Decomposição das Tarefas Atômicas (Padrão Ouro)

- **`task-01-seed-owners-and-pets.md`**:
  - Inserir os 10 donos e 13 pets canônicos em `src/main/resources/data.sql`.
  - Fast Test Command: `mvn test -Dtest=PetTypeResponseTest -q` (< 0.5s)
  - Model Tier: `free`

- **`task-02-owner-repository-queries.md`**:
  - Declarar as queries `findAllWithPets` e `findByLastNameContainingIgnoreCase` em `OwnerRepository.java`.
  - Teste Unitário Fast POJO/Mockito: `src/test/java/org/springframework/samples/petclinic/owner/OwnerResponseTest.java`.
  - Fast Test Command: `mvn test -Dtest=OwnerResponseTest -q` (< 0.2s)
  - Model Tier: `free`

- **`task-03-owner-controller-list-endpoint.md`**:
  - Implementar método `listOwners(@RequestParam(required = false) String lastName)` em `OwnerController.java`.
  - Teste Unitário Fast Mockito: `src/test/java/org/springframework/samples/petclinic/owner/OwnerControllerFilterTest.java`.
  - Fast Test Command: `mvn test -Dtest=OwnerControllerFilterTest -q` (< 0.6s)
  - Model Tier: `flash`

---

## 3. Reality Verifier (BDD de Produto)
Arquivo: `features/0003_owner_search.feature` (executa contra o servidor real HTTP em < 50ms):
- Cenário 1: Atendente lista donos cadastrados (contém "George" e "Franklin", status 200).
- Cenário 2: Atendente busca dono por sobrenome (contém "Betty" e "Davis", status 200).
