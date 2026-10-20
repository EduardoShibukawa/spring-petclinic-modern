Funcionalidade: Busca de Donos de Animais
  Como um atendente da recepção
  Eu quero listar todos os donos cadastrados
  Para encontrar o telefone e endereço do tutor

  Cenário: Atendente lista donos cadastrados
    Quando o atendente consulta a lista de donos
    Então a resposta deve conter "George"
    E a resposta deve conter "Franklin"
    E a solicitação deve ter sucesso com status 200
