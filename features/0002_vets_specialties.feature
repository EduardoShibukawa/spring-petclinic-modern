Funcionalidade: Catálogo de Especialidades Veterinárias e Tipos de Pets
  Como um desenvolvedor ou integrador de sistema
  Eu quero consultar especialidades e tipos de animais via API REST
  Para possibilitar o agendamento direcionado e validação de pets

  Cenário: Validação de compilação e testes da suite estendida
    Dado que o repositório está no estado inicial
    Quando eu executo o comando "mvn test -q"
    Então o código de saída deve ser 0

  Cenário: Empacotamento do JAR executável com novos endpoints de especialidades
    Dado que a branch atual é a "main"
    Quando eu executo o comando "mvn package -DskipTests -q && jar tf target/spring-petclinic-modern-0.1.0-SNAPSHOT.jar | grep -E 'SpecialtyRepository|PetTypeController'"
    Então o código de saída deve ser 0
    E a saída deve conter "SpecialtyRepository.class"
    E a saída deve conter "PetTypeController.class"
