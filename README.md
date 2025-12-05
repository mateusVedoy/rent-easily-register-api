# rent-easily-register-api

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Run

### in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
or
```shell script
quarkus dev
```

> **_NOTE:_**  Application will be listen on localhost 8080

## Build

### Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
or
```shell script
quarkus build
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Aside

### Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/rent-easily-register-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

### Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## About Project

### Architecture
This project uses the Hexagonal concept and separated by modules as bellow
![architecture](/src//main//resources//images/rent-easily-arch.png)

## Code Quality (Static Analysis)

Este projeto usa Checkstyle (para padronização de estilo de código) e PMD (para análise estática de bugs) para garantir a qualidade do código.

Para rodar todas as verificações de qualidade e falhar o build se qualquer violação for encontrada (recomendado antes de fazer um push), use a fase validate:

```bash
./mvnw validate
```
### Rodar apenas a verificação do Checkstyle
```bash
./mvnw checkstyle:check
```

### Rodar apenas a verificação do PMD

```bash
./mvnw pmd:check
```

## Gerando Relatórios (HTML)

Para gerar relatórios HTML amigáveis com todas as violações sem falhar o build, use o comando site:

```bash
./mvnw site
```

Após a execução, você pode abrir os relatórios no seu navegador:

- Relatório Checkstyle: ```target/site/checkstyle.html```
- Relatório PMD: ```target/site/pmd.html```

## Tests

### Unit tests

executa todos os tipos de testes
```bash

./mvnw test
```

ou

executa apenas os unitários
```bash

./mvnw test -Dcheckstyle.skip=true -Dpmd.skip=true

```

### Unit test + Integration test

```bash

./mvnw verify
```

ou 

Apenas os testes funcionais

```bash

./mvnw verify -Dcheckstyle.skip=true -Dpmd.skip=true

```