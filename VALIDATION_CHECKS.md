# Documentação de Validações e Análise Estática

Este documento descreve as ferramentas de análise estática e validação de estilo de código configuradas neste projeto. O objetivo é garantir a qualidade, consistência e manutenibilidade do código-fonte.

## Ferramentas Configuradas

1.  **Checkstyle**: Garante que o código siga um padrão de estilo e formatação.
2.  **PMD (Programming Mistake Detector)**: Analisa o código em busca de possíveis bugs, código não utilizado, e outras práticas de programação não recomendadas.

---

## 1. Checkstyle

O Checkstyle foca primariamente no **estilo e na formatação** do código. Ele verifica regras como o comprimento máximo de linhas, a ordem dos imports, o uso de espaços em branco, a nomeclatura de variáveis e muito mais. As regras específicas estão definidas no arquivo `checkstyle.xml`.

### Teste de Falseabilidade (Como Forçar uma Falha)

Para garantir que o Checkstyle está ativo e funcionando, podemos introduzir uma violação de estilo de propósito.

**Exemplo: Adicionar um import não utilizado**

Um dos checks mais comuns é a verificação de imports que foram declarados mas não são utilizados no arquivo.

1.  **Arquivo para alterar**: `src/main/java/rent/easily/advertisement/application/useCase/CreateAdvertisement.java`
2.  **Alteração**: Adicione a seguinte linha no início do arquivo, junto aos outros imports:
    ```java
    import java.util.ArrayList; // Import não utilizado
    ```
3.  **Comando para verificar**:
    ```shell
    ./mvnw validate
    ```
4.  **Resultado esperado**: O build irá falhar com uma mensagem do `maven-checkstyle-plugin` indicando a presença de um import não utilizado.

---

## 2. PMD (Programming Mistake Detector)

O PMD vai além do estilo e busca por **possíveis problemas lógicos e estruturais** no código. Ele pode detectar:
-   Variáveis, parâmetros ou métodos não utilizados (código morto).
-   Blocos `try-catch` vazios.
-   Criação desnecessária de objetos.
-   Uso de `System.out.println`, que é desencorajado em código de produção.

A análise do PMD neste projeto está configurada para escanear **apenas** o pacote `rent.easily.advertisement`.

### Teste de Falseabilidade (Como Forçar uma Falha)

Podemos introduzir um problema que o PMD é projetado para detectar.

**Exemplo: Adicionar `System.out.println`**

A ruleset `bestpractices` do PMD geralmente sinaliza o uso de `System.out.println` como uma má prática.

1.  **Arquivo para alterar**: `src/main/java/rent/easily/advertisement/application/useCase/CreateAdvertisement.java`
2.  **Alteração**: Dentro de qualquer método, adicione a seguinte linha:
    ```java
    System.out.println("Este é um teste para o PMD");
    ```
3.  **Comando para verificar**:
    ```shell
    ./mvnw validate
    ```
4.  **Resultado esperado**: O build irá falhar com uma mensagem do `maven-pmd-plugin` apontando o uso de `System.out.println`.
