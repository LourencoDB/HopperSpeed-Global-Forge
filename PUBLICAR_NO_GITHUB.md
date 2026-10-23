# Guia para publicar o HopperSpeed no GitHub

Este pacote reúne o código-fonte e documentação do HopperSpeed Global v1.0.0.

## 1. Crie um repositório

No GitHub, clique em **New repository**. Nome sugerido: `HopperSpeed-Global-Forge`.

Descrição sugerida:

> Mod Forge 1.21.8 que acelera globalmente os funis do Minecraft com /hopperspeed, sem alterar o TPS do mundo.

Marque **Public**. Se for usar os comandos de Git abaixo, crie o repositório **sem** adicionar README ou .gitignore pelo site (esses arquivos já estão no pacote).

## 2. Publique os arquivos

Extraia o ZIP e abra o CMD na pasta que contém `README.md` e `build.gradle`.

```bat
git init
git branch -M main
git add .
git commit -m "feat: publicacao inicial do HopperSpeed Global v1.0.0"
git remote add origin https://github.com/SEU-USUARIO/HopperSpeed-Global-Forge.git
git push -u origin main
```

Substitua `SEU-USUARIO` pelo seu usuário do GitHub. Se já tiver uma pasta `.git`, ignore o `git init` e configure o `remote` de acordo com seu repositório.

Você também pode arrastar os **arquivos extraídos** para a opção **Add file → Upload files** do GitHub, garantindo que a estrutura de pastas seja preservada.

## 3. Publique o mod compilado em Releases

Para que outras pessoas possam jogar **sem compilar o código**:

1. Copie de seu PC o JAR que já funcionou: `build\libs\hopperspeed-forge-1.21.8-1.0.0.jar`.
2. Entre na página do repositório e vá a **Releases → Draft a new release**.
3. Crie a tag `v1.0.0` e o título `HopperSpeed Global v1.0.0 — Forge 1.21.8`.
4. Cole o conteúdo de `RELEASE_NOTES_v1.0.0.md`.
5. Anexe o arquivo `.jar` **compilado e testado** na seção de arquivos da Release.
6. Publique.

O ZIP deste pacote **não inclui o .jar compilado**, pois o binário está no seu computador. Não publique o JAR de desenvolvimento com sufixos `dev`/`sources` se houver.

## 4. Sugestões de tópicos

`minecraft`, `minecraft-mod`, `minecraft-forge`, `forge`, `hoppers`, `automation`, `java`, `minecraft-1-21-8`

## 5. Antes de divulgar

- Confirme que `README.md` descreve o comportamento que você observou.
- Revise o titular da licença `LICENSE` e o campo `authors` de `src/main/resources/META-INF/mods.toml` caso queira inserir seu nome ou usuário.
- O workflow `.github/workflows/build.yml` está configurado para tentar compilar o projeto automaticamente; confira a aba **Actions** após o primeiro push.
