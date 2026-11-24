
package com.example.passwordchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.passwordchecker.ui.theme.PasswordCheckerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PasswordCheckerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    PasswordScreen()
                }
            }
        }
    }
}

@Composable
fun PasswordScreen() {

    // Przechowywanie wpisanego hasła
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val score = PasswordChecker.getScore(password)
    val strength = PasswordChecker.getStrength(password)

    // Kolor zależny od oceny hasła
    val strengthColor = when (strength) {
        "Strong" -> Color(0xFF4CAF50)
        "Medium" -> Color(0xFFFF9800)
        else -> Color(0xFFF44336)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Password Checker",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Check your password strength",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Pole do wpisania hasła
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Text(
                        if (passwordVisible) "Hide" else "Show"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Ocena hasła
        Text(
            text = "Password strength: $strength",
            color = strengthColor,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LinearProgressIndicator(
            progress = { score / 5f },
            modifier = Modifier.fillMaxWidth(),
            color = strengthColor
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Lista warunków
        PasswordRequirement(
            "At least 12 characters",
            PasswordChecker.hasMinLength(password)
        )

        PasswordRequirement(
            "Uppercase letter",
            PasswordChecker.hasUpperCase(password)
        )

        PasswordRequirement(
            "Lowercase letter",
            PasswordChecker.hasLowerCase(password)
        )

        PasswordRequirement(
            "Number",
            PasswordChecker.hasDigit(password)
        )

        PasswordRequirement(
            "Special character",
            PasswordChecker.hasSpecialChar(password)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Password is not saved or sent to a server.",
            fontSize = 12.sp,
            color = Color.Gray
        )

        Text(
            text = "Rule-based password assessment.",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

// Wyświetlanie pojedynczego warunku
@Composable
fun PasswordRequirement(
    description: String,
    isMet: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = if (isMet) "✓" else "✗",
            color = if (isMet) {
                Color(0xFF4CAF50)
            } else {
                Color(0xFFF44336)
            },
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(text = description)
    }
}
