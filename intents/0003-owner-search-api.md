# INTENT: API de Busca e Listagem de Donos de Animais (Owners)

- **ID**: `0003`
- **Projeto**: `spring-petclinic-modern`
- **Autor**: Equipe de Produto & Engenharia
- **Status**: DRAFT

---

## 1. Visão Geral do Caso de Uso
Na recepção da clínica veterinária, os atendentes precisam localizar tutores rapidamente para:
1. Listar todos os donos cadastrados na clínica.
2. Filtrar donos pelo sobrenome (`lastName`), permitindo buscas parciais sem distinção de maiúsculas/minúsculas.
3. Obter os dados de contato completos (endereço, cidade, telefone) e a lista de seus animais atendidos.

---

## 2. Necessidades de Negócio
- **Listagem Completa**: Quando nenhum filtro for informado, `GET /api/owners` deve retornar a lista de todos os tutores cadastrados com status HTTP 200.
- **Filtro por Sobrenome**: `GET /api/owners?lastName={termo}` deve filtrar os tutores cujo sobrenome contenha o termo buscado (case-insensitive).
- **Seed Data Padrão**: Carregar os dados clássicos de donos do PetClinic (George Franklin, Betty Davis, Eduardo Rodriquez) no `data.sql` para garantir reprodutibilidade hermética em desenvolvimento e testes.
- **Alta Performance de Teste**:
  - Testes unitários rápidos em Java (< 100ms) sem carregar o contexto pesado do Spring Boot.
  - Teste de aceitação da realidade de ponta a ponta (BDD Reality Verifier) validando o fluxo de produto em < 50ms contra o servidor real.
