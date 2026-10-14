package com.example.android.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.android.ui.components.NovaBiometricsButton
import com.example.android.ui.components.NovaDividerLabel
import com.example.android.ui.components.NovaFormField
import com.example.android.ui.components.NovaLoginBrandHeader
import com.example.android.ui.components.NovaPasswordField
import com.example.android.ui.components.NovaPrimaryCtaButton
import com.example.android.ui.components.NovaSocialAuthRow
import com.example.android.ui.theme.NovaSpacing
import com.example.android.ui.theme.NovaTheme

@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    onNavigateToSignUp: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.navigateToProfile) {
        if (uiState.navigateToProfile) {
            viewModel.onAction(LoginUiAction.NavigationHandled)
            onNavigateToProfile()
        }
    }

    LaunchedEffect(uiState.navigateToSignUp) {
        if (uiState.navigateToSignUp) {
            viewModel.onAction(LoginUiAction.NavigationHandled)
            onNavigateToSignUp()
        }
    }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onAction(LoginUiAction.DismissFeedback)
        }
    }

    LoginScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginUiAction) -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Centered Brand Logo & Welcome Headings
            NovaLoginBrandHeader()

            Spacer(modifier = Modifier.height(NovaSpacing.xl))

            // Email Field
            NovaFormField(
                value = uiState.email,
                onValueChange = { onAction(LoginUiAction.EmailChanged(it)) },
                label = "Email address",
                placeholder = "name@example.com",
                leadingIconRes = R.drawable.ic_mail,
                keyboardType = KeyboardType.Email,
                isError = uiState.emailError != null,
                errorMessage = uiState.emailError
            )

            Spacer(modifier = Modifier.height(NovaSpacing.md))

            // Password Field
            NovaPasswordField(
                value = uiState.password,
                onValueChange = { onAction(LoginUiAction.PasswordChanged(it)) },
                label = "Password",
                placeholder = "Enter your password",
                isPasswordVisible = uiState.isPasswordVisible,
                onToggleVisibility = { onAction(LoginUiAction.TogglePasswordVisibility) },
                isError = uiState.passwordError != null,
                errorMessage = uiState.passwordError
            )

            Spacer(modifier = Modifier.height(NovaSpacing.xs))

            // Remember me & Forgot Password Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onAction(LoginUiAction.ToggleRememberMe) }
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(androidx.compose.foundation.shape.RoundedCornerShape(6.dp))
                            .background(
                                if (uiState.rememberMe) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceContainerHigh
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.rememberMe) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "Remember me",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "Forgot password?",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier.clickable { onAction(LoginUiAction.ForgotPassword) }
                )
            }

            Spacer(modifier = Modifier.height(NovaSpacing.md))

            // Submit Button
            NovaPrimaryCtaButton(
                text = "Sign In",
                onClick = { onAction(LoginUiAction.Submit) },
                isLoading = uiState.isSubmitting
            )

            // Divider
            NovaDividerLabel(text = "OR CONTINUE WITH")

            // Social Login Buttons (Google & Apple)
            NovaSocialAuthRow(
                onGoogleClick = { onAction(LoginUiAction.SocialProviderClicked("Google")) },
                onAppleClick = { onAction(LoginUiAction.SocialProviderClicked("Apple")) }
            )

            Spacer(modifier = Modifier.height(NovaSpacing.sm))

            // Biometric Authentication
            NovaBiometricsButton(
                onClick = { onAction(LoginUiAction.BiometricClicked) }
            )

            Spacer(modifier = Modifier.height(NovaSpacing.xl))

            // Sign Up Footer Link
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Don't have an account?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.clickable { onAction(LoginUiAction.SignUpClicked) }
                )
            }

            Spacer(modifier = Modifier.height(NovaSpacing.lg))
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun LoginScreenPreview() {
    NovaTheme {
        LoginScreen(
            uiState = LoginUiState(),
            onAction = {}
        )
    }
}
