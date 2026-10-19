# TASK 04: Tratamento Canônico de Erros RFC 7807 (ProblemDetail)

- **ID**: `task-04-error-handling`
- **Target Files**: `src/main/java/org/springframework/samples/petclinic/config/GlobalExceptionHandler.java`
- **Fast Test Command**: `mvn test -Dtest=GlobalExceptionHandlerTest`
- **Feature Flag**: `PETCLINIC_FEATURE_VIRTUAL_THREADS=1`

---

## 1. Objetivo Cirúrgico
Implementar o `@RestControllerAdvice` utilizando a especificação RFC 7807 (`ProblemDetail`) nativa do Spring 6+ para respostas de erro de validação (400) e recursos não encontrados (404).

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-4.1**: `GlobalExceptionHandler` captura `MethodArgumentNotValidException` e formata resposta como `ProblemDetail` com status 400 e lista de violações de campos.
- [ ] **AC-4.2**: Captura de exceções de não encontrado (`ResourceNotFoundException`) retornando status 404 padronizado.
- [ ] **AC-4.3**: Fast Test `mvn test -Dtest=GlobalExceptionHandlerTest` passa 100%.
