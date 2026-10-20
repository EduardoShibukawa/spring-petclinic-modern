# LESSONS LEARNED — spring-petclinic-modern SDLC Engine

Memória persistente consolidada para modelos operários e orquestrador de tarefas.
> **Regra Rígida de Tamanho:** Este arquivo deve conter estritamente **<= 200 linhas**. Lições redundantes ou superadas são compactadas pelo motor DREAMS.

---

## 1. Regras Operacionais para Agentes de Código

1. **Escopo Cirúrgico**: Altere estritamente os arquivos designados na tarefa.
2. **Fast Unit Tests (POJO Puro)**: Use sempre testes sem `@SpringBootTest` para feedback inferior a 250ms nas tarefas atômicas.
3. **Persistência e Records**: DTOs devem ser implementados com Java 21 `record`.
4. **Tratamento de Erros RFC 7807**: Erros devem retornar `ProblemDetail` via `GlobalExceptionHandler`.

---

## 2. Síntese de Aprendizado (DREAMS Local)

### Consolidação DREAMS (2026-10-20 00:27 UTC)
- **DOMAIN_LOGIC / ExecutionError**: Ocorrido 2x. Verifique pré-condições e invariantes antes de alterar o estado.

