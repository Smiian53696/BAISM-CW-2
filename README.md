
# Password Checker

Prosta aplikacja mobilna do sprawdzania siły hasła.

## Technologie

- Kotlin
- Jetpack Compose
- Android SDK
- JUnit
- Bitrise CI/CD
- mobsfscan

## Funkcjonalności

- Sprawdzanie długości hasła
- Sprawdzanie wielkich i małych liter
- Sprawdzanie cyfr i znaków specjalnych
- Ocena hasła: Weak, Medium, Strong

## Testy

Projekt zawiera 10 testów jednostkowych
sprawdzających logikę aplikacji.

## CI/CD

Workflow safe_build:

1. Git Clone
2. Unit Tests
3. Security Scan - mobsfscan
4. Android Build
5. Deploy to Bitrise.io

