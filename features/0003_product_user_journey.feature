Funcionalidade: Jornada do Tutor no Catálogo de Especialidades e Tipos de Pets
  Como um tutor de animal de estimação
  Eu quero consultar especialidades e tipos disponíveis
  Para encontrar o profissional ideal para o meu pet

  Cenário: Tutor consulta especialidades disponíveis na clínica
    Quando o tutor consulta as especialidades disponíveis
    Então a resposta deve conter "radiology"
    E a resposta deve conter "surgery"
    E a resposta deve conter "dentistry"
    E a solicitação deve ter sucesso com status 200

  Cenário: Tutor busca veterinários pela especialidade de radiologia
    Quando o tutor busca veterinários pela especialidade "radiology"
    Então o veterinário "Helen" deve estar visível
    E a resposta deve conter "Leary"
    E a solicitação deve ter sucesso com status 200

  Cenário: Tutor consulta os tipos de animais atendidos
    Quando o tutor consulta os tipos de pets atendidos
    Então a resposta deve conter "dog"
    E a resposta deve conter "cat"
    E a resposta deve conter "lizard"
    E a solicitação deve ter sucesso com status 200
