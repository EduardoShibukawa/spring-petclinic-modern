# INTENT 0001: Modern Spring PetClinic REST API

## 1. Problema de Negócio
Uma rede de clínicas veterinárias necessita de um sistema centralizado e moderno para gerenciar o atendimento clínico de animais de estimação. O sistema deve possibilitar:
1. Cadastro e consulta de veterinários com suas respectivas especialidades clínicas.
2. Cadastro e gestão de proprietários (donos de pets) com dados de contato e endereço.
3. Registro de animais de estimação associados a um proprietário, especificando nome, data de nascimento e tipo do animal (ex: cão, gato, pássaro).
4. Agendamento e histórico de consultas/visitas veterinárias para cada pet.

## 2. Requisitos Técnicos & Limites
- **Linguagem & Framework**: Java 21 (LTS) e Spring Boot 3.4+.
- **Zero Código Legado**: Não utilizar ou copiar arquivos do repositório clássico. O design deve nascer puramente dos requisitos e padrões modernos.
- **Arquitetura**: Modular Monolith com forte coesão por domínio (`vets`, `owners`, `pets`, `visits`), REST API pura, DTOs imutáveis, validação declarativa e tratamento canônico de erros via RFC 7807 (Problem Details).
- **Persistência**: Spring Data JPA com banco leve (H2) para ciclo de testes instantâneo (< 2s).
- **Testabilidade**: Testes unitários rápidos e Reality Verifier (BDD) validando a aceitação do fluxo de ponta a ponta.
