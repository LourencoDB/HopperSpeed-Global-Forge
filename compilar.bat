@echo off
setlocal
cd /d "%~dp0"
if not exist "gradlew.bat" (
    echo.
    echo ERRO: gradlew.bat nao encontrado.
    echo Baixe o MDK oficial do Forge 1.21.8 e extraia os arquivos do projeto por cima dele.
    echo Veja o README.md para o passo a passo.
    echo.
    pause
    exit /b 1
)
call gradlew.bat build
if errorlevel 1 (
    echo.
    echo FALHA na compilacao. Copie o erro do terminal para diagnostico.
) else (
    echo.
    echo Pronto! Veja o .jar em build\libs\
)
pause
