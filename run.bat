@echo off
setlocal enabledelayedexpansion


set "SCRIPT_DIR=%~dp0"


set "PROJECT_DIR=%SCRIPT_DIR%Uni"


where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven is not installed or not in the system PATH.
    echo Please install Maven and add it to your system PATH.
    pause
    exit /b 1
)


if not exist "%PROJECT_DIR%\pom.xml" (
    echo Error: Maven project not found in %PROJECT_DIR%
    echo Make sure the Uni folder is in the same directory as this batch file.
    pause
    exit /b 1
)


cd /d "%PROJECT_DIR%"


start cmd /k "mvn spring-boot:run"


timeout /t 10 >nul


start http://localhost:8080/listings
start http://localhost:8080/h2-console

echo Application is starting...
echo Listings page and H2 Console will open in the browser
echo Close the command window to stop the application

pause
