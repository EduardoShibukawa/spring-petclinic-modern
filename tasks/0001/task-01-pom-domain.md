# TASK 01: Setup do Projeto Spring Boot 3.4 e Entidades Base (pom.xml)

- **ID**: `task-01-pom-domain`
- **Target Files**: `pom.xml`, `src/main/resources/application.properties`
- **Fast Test Command**: `mvn test-compile` (Meta: < 5s)
- **Feature Flag**: `PETCLINIC_FEATURE_VIRTUAL_THREADS=1`

---

## 1. Objetivo Cirúrgico
Configurar o `pom.xml` para Spring Boot 3.4.0 com Java 21, dependências essenciais (`spring-boot-starter-web`, `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `h2`, `spring-boot-starter-test`) e habilitar Virtual Threads no `application.properties`.

---

## 2. Critérios de Aceite (ACs)
- [ ] **AC-1.1**: `pom.xml` configurado com `spring-boot-starter-parent` versão 3.4.0 e `java.version` 21.
- [ ] **AC-1.2**: `application.properties` configurado com H2 in-memory, JPA ddl-auto=create-drop e `spring.threads.virtual.enabled=true`.
- [ ] **AC-1.3**: `PetClinicApplication.java` inicializa o contexto Spring Boot com sucesso.
- [ ] **AC-1.4**: Fast Test Command `mvn test-compile` executa e conclui com sucesso.
