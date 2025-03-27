@echo off
setlocal enabledelayedexpansion

REM Get the directory where the batch script is located
set "SCRIPT_DIR=%~dp0"

REM Construct the path to the Maven project
set "PROJECT_DIR=%SCRIPT_DIR%Uni"

REM Check if Maven is installed
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven is not installed or not in the system PATH.
    echo Please install Maven and add it to your system PATH.
    pause
    exit /b 1
)

REM Check if the project directory exists
if not exist "%PROJECT_DIR%\pom.xml" (
    echo Error: Maven project not found in %PROJECT_DIR%
    echo Make sure the Uni folder is in the same directory as this batch file.
    pause
    exit /b 1
)

REM Navigate to the project directory
cd /d "%PROJECT_DIR%"

REM Open a new command window to run the application
start cmd /k "mvn spring-boot:run"

REM Wait a few seconds to ensure the application starts
timeout /t 10 >nul

REM Open multiple URLs
start http://localhost:8080/listings
start http://localhost:8080/h2-console

echo Application is starting...
echo Listings page and H2 Console will open in the browser
echo Close the command window to stop the application

pause