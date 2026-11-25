package com.example.passwordchecker
import org.junit.Assert.*
import org.junit.Test

class PasswordCheckerTest {

    // Test długości hasła
    @Test
    fun testMinLength() {
        assertTrue(PasswordChecker.hasMinLength("Example1234!"))
        assertFalse(PasswordChecker.hasMinLength("abc"))
    }
    // Test wielkiej litery
    @Test
    fun testUpperCase() {
        assertTrue(PasswordChecker.hasUpperCase("Test"))
        assertFalse(PasswordChecker.hasUpperCase("test"))
    }
    // Test małej litery
    @Test
    fun testLowerCase() {
        assertTrue(PasswordChecker.hasLowerCase("Test"))
        assertFalse(PasswordChecker.hasLowerCase("TEST"))
    }
    // Test cyfry
    @Test
    fun testDigit() {
        assertTrue(PasswordChecker.hasDigit("Test123"))
        assertFalse(PasswordChecker.hasDigit("Test"))
    }
    // Test znaku specjalnego
    @Test
    fun testSpecialChar() {
        assertTrue(PasswordChecker.hasSpecialChar("Test!"))
        assertFalse(PasswordChecker.hasSpecialChar("Test"))
    }
    // Test pustego hasła
    @Test
    fun testEmptyPassword() {
        assertEquals(0, PasswordChecker.getScore(""))
    }
    // Test liczby punktów
    @Test
    fun testScore() {
        assertEquals(5, PasswordChecker.getScore("Example1234!"))
    }
    // Test słabego hasła
    @Test
    fun testWeakPassword() {
        assertEquals("Weak", PasswordChecker.getStrength("abc"))
    }
    // Test średniego hasła
    @Test
    fun testMediumPassword() {
        assertEquals("Medium", PasswordChecker.getStrength("Test123"))
    }
    // Celowo zmieniony test - Failed Path
    @Test
    fun testStrongPassword() {
        assertEquals("Weak", PasswordChecker.getStrength("Example1234!"))
    }
}
