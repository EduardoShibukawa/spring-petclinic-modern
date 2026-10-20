# TASK 01: Carga Inicial de Donos e Pets Canônicos (data.sql)

- **ID**: `task-0003-1`
- **Target File**: `src/main/resources/data.sql`
- **Test File**: `src/test/java/org/springframework/samples/petclinic/owner/OwnerResponseTest.java`
- **Fast Test Command**: `mvn test -Dtest=OwnerResponseTest -q`
- **Model Tier**: `flash`

---

## 1. Objetivo Cirúrgico
Em `src/main/resources/data.sql`:
Adicionar os inserts canônicos dos 10 donos e seus respectivos 13 pets de acordo com o PetClinic oficial:
1. Donos (tabela `owners` com colunas `id`, `first_name`, `last_name`, `address`, `city`, `telephone`):
   - id: 1, George Franklin, 110 W. Liberty St., Madison, 6085551023
   - id: 2, Betty Davis, 638 Cardinal Ave., Sun Prairie, 6085551749
   - id: 3, Eduardo Rodriquez, 2693 Commerce St., McFarland, 6085558763
   - id: 4, Harold Davis, 563 Friendly St., Windsor, 6085553198
   - id: 5, Peter McTavish, 2387 S. Fair Way, Madison, 6085552765
   - id: 6, Jean Coleman, 105 N. Lake St., Monona, 6085552654
   - id: 7, Jeff Black, 1450 Oak Blvd., Monona, 6085555387
   - id: 8, Maria Escobito, 345 Maple St., Madison, 6085557683
   - id: 9, David Schroeder, 2749 Blackhawk Trail, Madison, 6085559435
   - id: 10, Carlos Estaban, 2335 Independence La., Waunakee, 6085555487
2. Pets (tabela `pets` com colunas `id`, `name`, `birth_date`, `type_id`, `owner_id`):
   - id: 1, 'Leo', '2010-09-07', 1, 1 (cat, George Franklin)
   - id: 2, 'Basil', '2012-08-06', 1, 2 (cat, Betty Davis)
   - id: 3, 'Rosy', '2011-04-17', 2, 3 (dog, Eduardo Rodriquez)
   - id: 4, 'Jewel', '2010-03-07', 2, 3 (dog, Eduardo Rodriquez)
   - id: 5, 'Iggy', '2010-11-30', 3, 4 (lizard, Harold Davis)
   - id: 6, 'George', '2010-01-20', 1, 5 (cat, Peter McTavish)
   - id: 7, 'Samantha', '2012-09-04', 1, 6 (cat, Jean Coleman)
   - id: 8, 'Max', '2012-09-04', 1, 6 (cat, Jean Coleman)
   - id: 9, 'Lucky', '2011-08-06', 2, 7 (dog, Jeff Black)
   - id: 10, 'Mullingar', '2007-06-08', 2, 8 (dog, Maria Escobito)
   - id: 11, 'Freddy', '2010-03-09', 1, 9 (cat, David Schroeder)
   - id: 12, 'Lucky', '2010-06-24', 2, 10 (dog, Carlos Estaban)
   - id: 13, 'Sly', '2012-06-08', 1, 10 (cat, Carlos Estaban)

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-1.1**: Inserts dos 10 donos e 13 pets adicionados ao `data.sql`.
- [ ] **AC-1.2**: Fast Unit Test `mvn test -Dtest=OwnerResponseTest -q` passa 100% em < 0.5s.
