# TASK 02: Endpoint REST GET /api/owners com Filtragem por Sobrenome (OwnerController)

- **ID**: `task-0003-2`
- **Target File**: `src/main/java/org/springframework/samples/petclinic/owner/controller/OwnerController.java`
- **Test File**: `src/test/java/org/springframework/samples/petclinic/owner/OwnerControllerFilterTest.java`
- **Fast Test Command**: `mvn test -Dtest=OwnerControllerFilterTest -q`
- **Model Tier**: `flash`

---

## 1. Objetivo Cirúrgico
Em `src/main/java/org/springframework/samples/petclinic/owner/controller/OwnerController.java`:
1. Adicionar o endpoint `@GetMapping` na raiz do controller (`/api/owners`):
   ```java
   @GetMapping
   public ResponseEntity<List<OwnerResponse>> listOwners(
       @RequestParam(required = false) String lastName
   ) {
       List<Owner> owners;
       if (lastName != null && !lastName.trim().isEmpty()) {
           owners = ownerRepository.findByLastNameContainingIgnoreCase(lastName.trim());
       } else {
           owners = ownerRepository.findAllWithPets();
       }
       List<OwnerResponse> response = owners.stream()
           .map(OwnerResponse::from)
           .toList();
       return ResponseEntity.ok(response);
   }
   ```
2. Garantir import de `java.util.List` e anotações `@GetMapping` e `@RequestParam`.

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-2.1**: `GET /api/owners` sem parâmetro delega para `ownerRepository.findAllWithPets()`.
- [ ] **AC-2.2**: `GET /api/owners?lastName={name}` delega para `ownerRepository.findByLastNameContainingIgnoreCase(...)`.
- [ ] **AC-2.3**: Retorna status HTTP 200 com a lista de `OwnerResponse`.
- [ ] **AC-2.4**: Fast Unit Test `mvn test -Dtest=OwnerControllerFilterTest -q` passa 100% em < 0.6s.
