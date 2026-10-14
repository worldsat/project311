package com.example.android.ui.login

/**
 * Immutable UI State for the Login screen.
 */
data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val isSubmitting: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null,
    val feedbackMessage: String? = null,
    val navigateToProfile: Boolean = false,
    val navigateToSignUp: Boolean = false
)

/**
 * User actions on the Login screen.
 */
sealed interface LoginUiAction {
    data class EmailChanged(val value: String) : LoginUiAction
    data class PasswordChanged(val value: String) : LoginUiAction
    data object TogglePasswordVisibility : LoginUiAction
    data object ToggleRememberMe : LoginUiAction
    data object Submit : LoginUiAction
    data object ForgotPassword : LoginUiAction
    data object SignUpClicked : LoginUiAction
    data class SocialProviderClicked(val provider: String) : LoginUiAction
    data object BiometricClicked : LoginUiAction
    data object NavigationHandled : LoginUiAction
    data object DismissFeedback : LoginUiAction
}
