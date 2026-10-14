package com.example.android.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.android.ui.components.NovaDividerLabel
import com.example.android.ui.components.NovaFormField
import com.example.android.ui.components.NovaPasswordField
import com.example.android.ui.components.NovaPrimaryCtaButton
import com.example.android.ui.components.NovaSignUpBrandHeader
import com.example.android.ui.components.NovaSocialAuthRow
import com.example.android.ui.components.NovaWavyBackground
import com.example.android.ui.theme.NovaSpacing
import com.example.android.ui.theme.NovaTheme

@Composable
fun SignUpRoute(
    viewModel: SignUpViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.navigateToProfile) {
        if (uiState.navigateToProfile) {
            viewModel.onAction(SignUpUiAction.NavigationHandled)
            onNavigateToProfile()
        }
    }

    LaunchedEffect(uiState.navigateToLogin) {
        if (uiState.navigateToLogin) {
            viewModel.onAction(SignUpUiAction.NavigationHandled)
            onNavigateToLogin()
        }
    }

    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onAction(SignUpUiAction.DismissFeedback)
        }
    }

    SignUpScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onAction: (SignUpUiAction) -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        // Shared Dynamic Wavy Background
        NovaWavyBackground()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .statusBarsPadding()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Bar Navigation Back Action in floating frosted circle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onAction(SignUpUiAction.BackClicked) },
                        modifier = Modifier
                            .size(44.dp)
                            .shadow(6.dp, CircleShape, spotColor = Color(0xFF2514A5).copy(alpha = 0.15f))
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.94f))
                            .border(1.dp, Color.White, CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back to sign in",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                // Compact Brand Header
                NovaSignUpBrandHeader()

                Spacer(modifier = Modifier.height(NovaSpacing.md))

                // White Rounded Registration Form Card with Glassmorphic Styling
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            shape = RoundedCornerShape(28.dp),
                            spotColor = Color(0xFF2514A5).copy(alpha = 0.18f),
                            ambientColor = Color.Black.copy(alpha = 0.04f)
                        )
                        .clip(RoundedCornerShape(28.dp))
                        .background(Color.White.copy(alpha = 0.94f))
                        .border(
                            width = 1.5.dp,
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.White,
                                    Color(0xFFC7D2FE).copy(alpha = 0.7f),
                                    Color(0xFFE0E0FF).copy(alpha = 0.4f)
                                )
                            ),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 22.dp)
                ) {
                    // Full Name Field
                    NovaFormField(
                        value = uiState.fullName,
                        onValueChange = { onAction(SignUpUiAction.FullNameChanged(it)) },
                        label = "Full Name",
                        placeholder = "Alex Morgan",
                        leadingIconRes = R.drawable.ic_person,
                        isError = uiState.nameError != null,
                        errorMessage = uiState.nameError
                    )

                Spacer(modifier = Modifier.height(NovaSpacing.sm))

                // Email Field
                NovaFormField(
                    value = uiState.email,
                    onValueChange = { onAction(SignUpUiAction.EmailChanged(it)) },
                    label = "Email address",
                    placeholder = "alex.morgan@domain.com",
                    leadingIconRes = R.drawable.ic_mail,
                    keyboardType = KeyboardType.Text,
                    isError = uiState.emailError != null,
                    errorMessage = uiState.emailError
                )

                Spacer(modifier = Modifier.height(NovaSpacing.sm))

                // Password Field
                NovaPasswordField(
                    value = uiState.password,
                    onValueChange = { onAction(SignUpUiAction.PasswordChanged(it)) },
                    label = "Password",
                    placeholder = "••••••••••••",
                    isPasswordVisible = uiState.isPasswordVisible,
                    onToggleVisibility = { onAction(SignUpUiAction.TogglePasswordVisibility) },
                    isError = uiState.passwordError != null,
                    errorMessage = uiState.passwordError
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Password Strength Indicator Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        for (i in 1..4) {
                            val isActive = i <= uiState.passwordStrengthLevel
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (isActive) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.primaryContainer
                                    )
                            )
                        }

                        if (uiState.passwordStrengthLabel.isNotEmpty()) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = uiState.passwordStrengthLabel,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    Text(
                        text = "At least 8 chars",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(NovaSpacing.sm))

                // Confirm Password Field
                NovaPasswordField(
                    value = uiState.confirmPassword,
                    onValueChange = { onAction(SignUpUiAction.ConfirmPasswordChanged(it)) },
                    label = "Confirm Password",
                    placeholder = "••••••••••••",
                    leadingIconRes = R.drawable.ic_verified,
                    isPasswordVisible = uiState.isPasswordVisible,
                    onToggleVisibility = { onAction(SignUpUiAction.TogglePasswordVisibility) },
                    trailingValidationIcon = if (uiState.isConfirmPasswordMatching) {
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check_circle),
                                contentDescription = "Password confirmed",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    } else null,
                    isError = uiState.confirmPasswordError != null,
                    errorMessage = uiState.confirmPasswordError
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Consent Checkbox Row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onAction(SignUpUiAction.ToggleTermsAccepted) }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                                if (uiState.acceptedTerms) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.surfaceContainerHigh
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (uiState.acceptedTerms) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "I agree to the ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Terms of Service",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onAction(SignUpUiAction.TermsClicked) }
                        )
                        Text(
                            text = " and ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "Privacy Policy",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                textDecoration = TextDecoration.Underline
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable { onAction(SignUpUiAction.PrivacyClicked) }
                        )
                    }
                }

                if (uiState.termsError != null) {
                    Text(
                        text = uiState.termsError,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(NovaSpacing.sm))

                // Submit CTA Button
                NovaPrimaryCtaButton(
                    text = "Create Account",
                    onClick = { onAction(SignUpUiAction.Submit) },
                    isLoading = uiState.isSubmitting
                )
            }

            // Divider
            NovaDividerLabel(text = "OR REGISTER WITH")

            // Social Buttons
            NovaSocialAuthRow(
                onGoogleClick = { onAction(SignUpUiAction.SocialProviderClicked("Google")) },
                onAppleClick = { onAction(SignUpUiAction.SocialProviderClicked("Apple")) }
            )

            Spacer(modifier = Modifier.height(NovaSpacing.lg))

            // Footer Sign In Link in a floating frosted pill
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .shadow(
                            elevation = 6.dp,
                            shape = RoundedCornerShape(20.dp),
                            spotColor = Color(0xFF2514A5).copy(alpha = 0.15f)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(alpha = 0.94f))
                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "Already have an account?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "Sign In",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.clickable { onAction(SignUpUiAction.SignInClicked) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(NovaSpacing.md))
        }
    }
}
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SignUpScreenPreview() {
    NovaTheme {
        SignUpScreen(
            uiState = SignUpUiState(
                fullName = "Alex Morgan",
                email = "alex.morgan@domain.com",
                password = "••••••••••••",
                confirmPassword = "••••••••••••",
                acceptedTerms = true
            ),
            onAction = {}
        )
    }
}
