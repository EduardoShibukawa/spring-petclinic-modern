# SPEC DRAFT 0001: Modern Spring PetClinic REST API

## 1. Visão Geral da Arquitetura
Migração conceitual do clássico PetClinic para uma arquitetura moderna em **Java 21** e **Spring Boot 3.4+**, eliminando renderização de views em servidor (Thymeleaf/JSP) em favor de uma **API RESTful modular**, tipada e orientada a contratos imutáveis com **Java Records**.

### 1.1 Estrutura de Pacotes (Modular Monolith)
```text
src/main/java/org/springframework/samples/petclinic/
├── PetClinicApplication.java
├── config/
│   ├── OpenApiConfig.java
│   └── GlobalExceptionHandler.java (RFC 7807 ProblemDetail)
├── vet/
│   ├── model/ (Vet, Specialty)
│   ├── dto/ (VetResponse, SpecialtyDto)
│   ├── repository/ (VetRepository)
│   └── controller/ (VetController)
├── owner/
│   ├── model/ (Owner, Pet, PetType)
│   ├── dto/ (CreateOwnerRequest, OwnerResponse, PetRequest, PetResponse)
│   ├── repository/ (OwnerRepository, PetRepository)
│   └── controller/ (OwnerController)
└── visit/
    ├── model/ (Visit)
    ├── dto/ (CreateVisitRequest, VisitResponse)
    ├── repository/ (VisitRepository)
    └── controller/ (VisitController)
```

---

## 2. Cenário de Aceitação do Reality Verifier (BDD)
Arquivo: `features/petclinic_workflow.feature`

```gherkin
# language: pt
Funcionalidade: Fluxo Completo de Gestão da Clínica Veterinária
  Como um atendente da clínica
  Eu quero cadastrar veterinários, donos, pets e agendar consultas
  Para garantir o ciclo de atendimento veterinário

  Cenário: Cadastro e agendamento de consulta com sucesso
    Dado que o serviço Spring PetClinic está em execução
    Quando eu executo o comando "curl -s -X POST http://localhost:8080/api/owners -H 'Content-Type: application/json' -d '{\"firstName\":\"George\",\"lastName\":\"Franklin\",\"address\":\"110 W. Liberty St.\",\"city\":\"Madison\",\"telephone\":\"6085551023\"}'"
    Então a resposta deve conter "\"firstName\":\"George\""
    E a resposta deve conter "\"id\":"
    Quando eu executo o comando "curl -s -X POST http://localhost:8080/api/owners/1/pets -H 'Content-Type: application/json' -d '{\"name\":\"Leo\",\"birthDate\":\"2020-09-07\",\"type\":\"cat\"}'"
    Então a resposta deve conter "\"name\":\"Leo\""
    Quando eu executo o comando "curl -s -X POST http://localhost:8080/api/owners/1/pets/1/visits -H 'Content-Type: application/json' -d '{\"visitDate\":\"2026-10-20\",\"description\":\"Vacinação anual\"}'"
    Então a resposta deve conter "\"description\":\"Vacinação anual\""
```

---

## 3. OPEN QUESTIONS & TRADE-OFFS (Para Decisão Humana no Gate)

1. **Padrão Arquitetural**:
   - *Opção A (Recomendada)*: **Modular Monolith (Package-by-Feature)**. Cada módulo (`vet`, `owner`, `visit`) possui seus próprios modelos, DTOs e controladores, desacoplando domínios.
   - *Opção B*: Package-by-Layer tradicional (`model/`, `repository/`, `controller/` com todas as entidades misturadas).
2. **Imutabilidade e Tipagem em Java 21**:
   - *Opção A (Recomendada)*: **Java 21 Records** para todas as entradas e saídas de API (`CreateOwnerRequest`, `OwnerResponse`, etc.) com validações declarativas (`jakarta.validation`).
   - *Opção B*: Classes convencionais com Getters, Setters e construtores mutáveis.
3. **Padrão de Erro da API**:
   - *Opção A (Recomendada)*: **RFC 7807 (`ProblemDetail`)** nativo do Spring 6/Boot 3, fornecendo títulos, status, detalhes e URI de instâncias em caso de 400 Bad Request ou 404 Not Found.
   - *Opção B*: Formato de JSON ad-hoc customizado `{ "error": "...", "timestamp": "..." }`.
4. **Concorrência e Throughput (Java 21)**:
   - *Opção A (Recomendada)*: Ativar **Virtual Threads** via `spring.threads.virtual.enabled=true` para escalabilidade em requisições bloqueantes I/O com custo mínimo de memória.
   - *Opção B*: Thread pool tradicional do Tomcat.
5. **Persistência de Dados**:
   - *Opção A (Recomendada)*: H2 in-memory com JPA Hibernate, com DDL automático e inicialização em < 1s para o testbed autônomo.
   - *Opção B*: PostgreSQL externo via Docker.
