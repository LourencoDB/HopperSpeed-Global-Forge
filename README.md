# HopperSpeed Global 🚀

**Acelere todos os funis (*hoppers*) do Minecraft Java 1.21.8 com um único comando, sem acelerar o restante do jogo.**

Mod para **Minecraft Forge 1.21.8**, desenvolvido e testado com **Forge 58.1.26**. A velocidade se aplica aos funis vanilla existentes e aos novos, em qualquer dimensão **enquanto seus chunks estiverem carregados**.

> ✅ **Testado em jogo:** o mod carregou no Forge 1.21.8, registrou o comando e acelerou a passagem de itens nos funis. A compilação com ForgeGradle 7 também foi concluída com sucesso no Windows 11. O projeto pode precisar de ajustes para outras versões e combinações de mods.

## ✨ Recursos

- **Velocidade global:** um comando configura todos os funis vanilla do mundo, inclusive os já colocados.
- **Sem varredura de blocos:** a alteração atua no cooldown de transferência dos funis durante a execução do jogo.
- **Não altera o TPS do mundo:** redstone, plantações e demais sistemas não são acelerados pelo comando.
- **Configuração persistente:** o intervalo é salvo e continua ativo depois de reabrir a instalação do jogo.
- **Funciona no Overworld, Nether e End:** desde que os chunks dessas dimensões estejam carregados.
- **Multiplayer:** em servidor dedicado, instale o mod **no servidor** e use o comando com permissão de operador.

## ⚡ Comandos

| Comando | Intervalo entre transferências | Taxa teórica máxima por funil | Comparação com vanilla |
| --- | ---: | ---: | ---: |
| `/hopperspeed 1` | 1 tick | 20 itens/s | 8× |
| `/hopperspeed 2` | 2 ticks | 10 itens/s | 4× |
| `/hopperspeed 4` | 4 ticks | 5 itens/s | 2× |
| `/hopperspeed 8` | 8 ticks | 2,5 itens/s | 1× (normal) |
| `/hopperspeed reset` | 8 ticks | 2,5 itens/s | Padrão |
| `/hopperspeed` | — | — | Exibe a configuração atual |

É possível definir **qualquer valor inteiro de 1 a 8**, por exemplo `/hopperspeed 3`.

**Permissão:** nível 2 de operador (OP). Em um mundo singleplayer criado sem comandos, use `Esc → Abrir para LAN → Permitir comandos: SIM` antes de executar o comando. Essa permissão temporária não fica habilitada automaticamente nas próximas sessões.

**Importante:** os números acima são limites teóricos para um funil transferindo um item por operação; a taxa real depende dos inventários, do ritmo de produção da farm e de outros mods.

## 📥 Instalação do mod (jogadores)

1. Instale **Minecraft Java 1.21.8** com **Forge 58.1.26**.
2. Acesse a seção **Releases** deste repositório, se houver uma versão publicada com o `.jar` compilado.
3. Copie `hopperspeed-forge-1.21.8-1.0.0.jar` para a pasta `mods` **da instalação Forge correta**.
4. Inicie o jogo, abra seu mundo e execute `/hopperspeed 1` (com comandos habilitados).

No Windows, o diretório padrão costuma ser `%appdata%\.minecraft\mods`. Launchers com perfis/instâncias separados podem usar outra pasta.

> **Se não existir um JAR na página de Releases**, siga o guia de compilação abaixo. Este repositório contém o **código-fonte**, não um binário pré-compilado.

## 🛠️ Compilar a partir do código-fonte

### Requisitos

- **JDK 21** (não apenas o JRE). Projeto testado com Java 21.
- **Forge MDK 1.21.8 — 58.1.26**: [downloads oficiais do Forge](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.21.8.html).
- Conexão à internet na primeira compilação para obter as dependências.

### Windows: usando o Gradle Wrapper do MDK

Este repositório **não inclui** os arquivos binários/scripts do Gradle Wrapper. Eles vêm no MDK oficial do Forge.

1. Baixe este repositório (Code → Download ZIP) e extraia em uma pasta, ou use `git clone`.
2. Baixe o **MDK da versão 58.1.26** e extraia em uma pasta temporária.
3. Copie **apenas** `gradlew`, `gradlew.bat` e a pasta `gradle/` do MDK para a raiz do projeto baixado. **Não substitua** `src/`, `build.gradle`, `gradle.properties` ou `settings.gradle` deste repositório.
4. Abra um terminal na raiz do projeto e execute:

   ```bat
   gradlew.bat clean build
   ```

   Ou dê dois cliques em `compilar.bat`.

5. Se aparecer `BUILD SUCCESSFUL`, procure o mod em:

   ```text
   build\libs\hopperspeed-forge-1.21.8-1.0.0.jar
   ```

Alternativamente, quem já tem o **Gradle 9.3.1** instalado pode rodar `gradle clean build` sem copiar o Wrapper. O workflow do GitHub Actions também utiliza essa versão do Gradle.

### Linux/macOS

Com o `gradlew` e `gradle/` do MDK copiados para este projeto:

```bash
chmod +x gradlew
./gradlew clean build
```

## ⚙️ Configuração e funcionamento

A configuração é armazenada em `config/hopperspeed.properties`, **na pasta da instalação ou servidor**. É uma configuração global da instância, **não específica de cada save**. O valor padrão é **8 ticks**, até que alguém com permissão execute outro comando.

O mod aplica um [Mixin](src/main/java/dev/hopperspeed/mixin/HopperBlockEntityMixin.java) em `HopperBlockEntity#setCooldown(int)` e substitui somente os intervalos vanilla de 7 ou 8 ticks por um valor configurado menor. A transferência continua sendo de **um item por operação**. O mod não cria carregadores de chunks e não altera o limite de capacidade dos inventários.

**Limitações conhecidas:**

- Funis em chunks descarregados continuam parados até o jogo carregar esses chunks.
- A aceleração se destina aos **funis vanilla**, não necessariamente aos funis de outros mods ou minecarts com funil.
- Farms com muitos funis ativos podem exigir mais processamento do servidor.
- Quando você altera a velocidade, alguns funis podem completar primeiro o cooldown que já estava em andamento.
- Por envolver Mixin, outros mods que modificam o mesmo método podem ter conflitos.

Faça **backup do mundo** antes de testar novos mods, especialmente em servidores e modpacks.

## 🗂️ Estrutura do projeto

```text
src/main/java/dev/hopperspeed/
├── HopperSpeedMod.java              # Registro e tratamento dos comandos
├── HopperSpeedSettings.java         # Carregamento e salvamento da configuração
└── mixin/
    └── HopperBlockEntityMixin.java # Ajuste do cooldown vanilla
src/main/resources/
├── META-INF/mods.toml               # Metadados do Forge
├── hopperspeed.mixins.json          # Configuração do Mixin
└── pack.mcmeta                      # Metadados de recursos (Minecraft 1.21.8)
```

## 🐛 Problemas e sugestões

Abra uma **Issue** com a versão exata do Minecraft e Forge, o comando utilizado, o comportamento esperado, o resultado obtido e (se relevante) o trecho de `logs/latest.log` que mostre o erro. Antes de anexar logs, confira se não há informações pessoais que você queira manter privadas.

Melhorias e correções via Pull Request são bem-vindas. Consulte [CONTRIBUTING.md](CONTRIBUTING.md) e [CHANGELOG.md](CHANGELOG.md).

## 📜 Licença e créditos

Distribuído sob a **licença MIT**. Consulte [LICENSE](LICENSE).

Projeto criado em colaboração entre o mantenedor da comunidade e o **ChatGPT (OpenAI)**. A compilação, os testes em jogo e a validação prática foram realizados pelo mantenedor. Você pode usar, estudar, modificar e redistribuir o código conforme os termos da licença.

*Minecraft é uma marca da Mojang Studios/Microsoft. Este é um projeto independente, sem afiliação oficial.*
