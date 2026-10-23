# Changelog

Todas as mudanças relevantes deste projeto são registradas aqui.

## [1.0.0] - 2026-10-23

### Adicionado
- Comando `/hopperspeed` com valores de 1 a 8 ticks e opção `reset`.
- Aplicação global da velocidade aos funis vanilla em chunks carregados.
- Persistência das configurações em `config/hopperspeed.properties`.
- Compatibilidade validada em jogo com Minecraft Java 1.21.8 e Forge 58.1.26.

### Corrigido durante os testes
- Mapeamentos do Mixin para o ForgeGradle 7.
- Registro de comandos via `RegisterCommandsEvent.BUS` (EventBus 7).
- Inclusão de `pack.mcmeta` exigido para carregamento do mod.
- Documentação da permissão de nível 2 para os comandos.
