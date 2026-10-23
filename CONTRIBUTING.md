# Contribuindo com o HopperSpeed

Obrigado por querer melhorar o projeto! 🙌

1. Confira as Issues abertas antes de criar uma nova.
2. Para reportar bugs, informe **Minecraft 1.21.8**, versão do **Forge**, mods adicionais, comportamento esperado e ocorrido e etapas para reproduzir.
3. Faça um fork, crie uma branch específica e envie um Pull Request com descrição clara da mudança.
4. Rode `gradlew.bat clean build` (Windows) ou `./gradlew clean build` (Linux/macOS) com **JDK 21** e o Wrapper fornecido pelo Forge MDK. Em instalações com Gradle 9.3.1, `gradle build` também serve.
5. Se alterar a lógica dos funis, teste pelo menos os comandos `/hopperspeed 1`, `/hopperspeed 8` e `/hopperspeed reset`, com funis já existentes e novos, em mundo de teste.
6. Não envie pastas `.gradle/`, `build/`, `run/`, nem os saves do Minecraft no Pull Request.

**Compatibilidade:** evite mudanças que acelerem o TPS global ou carreguem chunks à força. A proposta do mod é modificar somente o cooldown dos funis vanilla.

**Créditos:** contribuições aceitas poderão constar no histórico do GitHub, nos commits e nas notas de versão. O código está sob a licença MIT.
