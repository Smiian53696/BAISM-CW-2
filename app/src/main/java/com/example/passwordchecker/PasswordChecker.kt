package com.example.passwordchecker

object PasswordChecker {

    // Sprawdzenie długości hasła
    fun hasMinLength(password: String): Boolean {
        return password.length >= 12
    }

    // Sprawdzenie wielkiej litery
    fun hasUpperCase(password: String): Boolean {
        return password.any { it.isUpperCase() }
    }

    // Sprawdzenie małej litery
    fun hasLowerCase(password: String): Boolean {
        return password.any { it.isLowerCase() }
    }

    // Sprawdzenie cyfry
    fun hasDigit(password: String): Boolean {
        return password.any { it.isDigit() }
    }

    // Sprawdzenie znaku specjalnego
    fun hasSpecialChar(password: String): Boolean {
        return password.any { !it.isLetterOrDigit() && !it.isWhitespace() }
    }

    // Obliczanie liczby spełnionych warunków
    fun getScore(password: String): Int {
        var score = 0

        if (hasMinLength(password)) score++
        if (hasUpperCase(password)) score++
        if (hasLowerCase(password)) score++
        if (hasDigit(password)) score++
        if (hasSpecialChar(password)) score++

        return score
    }

    fun getStrength(password: String): String { // Ocena hasła
        return when (getScore(password)) {
            0, 1, 2 -> "Weak"
            3, 4 -> "Medium"
            5 -> "Strong"
            else -> "Weak"
        }
    }
}

