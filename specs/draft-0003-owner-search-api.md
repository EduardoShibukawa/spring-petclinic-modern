# SPEC DRAFT 0003: API de Busca e Listagem de Donos de Animais (Owners)

> **Status:** DRAFT (Aguardando Revisão Humana)  
> **Target Project:** `duh-homelab/spring-petclinic-modern`  
> **Padrão de Execução:** Composição Pura, Fast POJO Tests e Reality Verifier BDD

---

## 1. Visão Geral Técnica e Arquitetura

Esta especificação implementa a funcionalidade de consulta de donos de animais na API REST moderna do Spring PetClinic, permitindo aos atendentes da recepção listar todos os tutores cadastrados ou buscar por sobrenome com case-insensitivity.

### Componentes Afetados:
1. **Dados Iniciais (`src/main/resources/data.sql`)**:
   - Inserção de donos padrão (ex: `George Franklin`, `Betty Davis`, `Eduardo Rodriquez`) e vinculação com seus respectivos pets.
2. **Camada de Dados (`OwnerRepository.java`)**:
   - Consulta otimizada com `LEFT JOIN FETCH o.pets` para evitar N+1 queries:
     `@Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets WHERE LOWER(o.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")`
     `List<Owner> findByLastNameContainingIgnoreCase(@Param("lastName") String lastName);`
   - Consulta completa:
     `@Query("SELECT DISTINCT o FROM Owner o LEFT JOIN FETCH o.pets")`
     `List<Owner> findAllWithPets();`
3. **Camada de Exposição (`OwnerController.java`)**:
   - `@GetMapping` em `/api/owners`:
     ```java
     @GetMapping
     public ResponseEntity<List<OwnerResponse>> listOwners(
         @RequestParam(required = false) String lastName
     )
     ```
   - Retorna lista de DTOs `OwnerResponse` existentes com status HTTP 200.

---

## 2. Esboço das Tarefas Atômicas (Padrão Ouro)

- **`task-01-repo-and-seed.md`**:
  - **Target File**: `src/main/java/org/springframework/samples/petclinic/owner/repository/OwnerRepository.java` e `src/main/resources/data.sql`
  - **Test File**: `src/test/java/org/springframework/samples/petclinic/owner/OwnerRepositoryTest.java`
  - **Fast Test Command**: `mvn test -Dtest=OwnerRepositoryTest -q`
  - **Model Tier**: `flash`

- **`task-02-owner-controller.md`**:
  - **Target File**: `src/main/java/org/springframework/samples/petclinic/owner/controller/OwnerController.java`
  - **Test File**: `src/test/java/org/springframework/samples/petclinic/owner/OwnerControllerListTest.java`
  - **Fast Test Command**: `mvn test -Dtest=OwnerControllerListTest -q`
  - **Model Tier**: `flash`

---

## 3. Reality Verifier (BDD de Produto)

O arquivo `features/0003_owner_search.feature` valida o comportamento de aceitação na aplicação real:

```gherkin
Funcionalidade: Busca de Donos de Animais
  Como um atendente da recepção
  Eu quero listar todos os donos cadastrados
  Para encontrar o telefone e endereço do tutor

  Cenário: Atendente lista donos cadastrados
    Quando o atendente consulta a lista de donos
    Então a resposta deve conter "George"
    E a resposta deve conter "Franklin"
    E a solicitação deve ter sucesso com status 200

  Cenário: Atendente busca dono pelo sobrenome
    Quando o atendente busca donos pelo sobrenome "Davis"
    Então a resposta deve conter "Betty"
    E a solicitação deve ter sucesso com status 200
```

---

## 4. OPEN QUESTIONS (Perguntas Abertas para Alinhamento Humano)

1. **Paginação vs. Lista Direta:**
   - Devemos retornar `List<OwnerResponse>` direta para manter a simplicidade e a latência sub-50ms (idêntico ao endpoint de `vets`), ou você prefere já introduzir paginação Spring (`Page<OwnerResponse>`)?
   - *(Recomendação: `List<OwnerResponse>` direta agora para manter os testes do Reality Verifier ultra-rápidos e consistentes com o resto da API).*

2. **Comportamento quando a busca não encontrar ninguém:**
   - Quando um termo de busca por sobrenome não retornar resultados (ex: `?lastName=Desconhecido`), devemos retornar uma lista vazia `[]` com HTTP 200 ou HTTP 404 Not Found?
   - *(Recomendação: HTTP 200 com array vazio `[]`, que é o padrão canônico REST e evita exceções no frontend).*

3. **Carregamento dos Pets (Prevenção de N+1 Queries):**
   - Na listagem de donos, devemos carregar os pets usando `LEFT JOIN FETCH` para garantir que o banco execute apenas 1 query rápida?
   - *(Recomendação: Sim, `LEFT JOIN FETCH` garante alta performance).*
