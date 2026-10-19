# language: pt
Funcionalidade: Verificação de Aceitação e Integridade do Modern PetClinic
  Como um arquiteto de software
  Eu quero validar a integridade da suíte de testes de domínio e a geração do artefato executável
  Para certificar que o sistema está pronto para produção com Java 21 e Spring Boot 3.4

  Cenário: Suíte de testes unitários e de integração de domínio
    Quando eu executo o comando "mvn test"
    Então o código de saída deve ser 0
    E a resposta deve conter "BUILD SUCCESS"
    E a resposta deve conter "Failures: 0, Errors: 0"

  Cenário: Geração do artefato JAR executável autônomo
    Quando eu executo o comando "mvn package -DskipTests"
    Então o código de saída deve ser 0
    E a resposta deve conter "Building jar"
    E a resposta deve conter "BUILD SUCCESS"
