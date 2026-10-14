package com.example.android.ui.signup

/**
 * Immutable UI State for the Sign Up screen.
 */
data class SignUpUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val acceptedTerms: Boolean = false,
    val nameError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,
    val termsError: String? = null,
    val isSubmitting: Boolean = false,
    val feedbackMessage: String? = null,
    val navigateToProfile: Boolean = false,
    val navigateToLogin: Boolean = false
) {
    val passwordStrengthLevel: Int
        get() = when {
            password.isEmpty() -> 0
            password.length < 4 -> 1
            password.length < 8 -> 2
            password.length < 12 -> 3
            else -> 4
        }

    val passwordStrengthLabel: String
        get() = when (passwordStrengthLevel) {
            0 -> ""
            1 -> "Weak"
            2 -> "Fair security"
            3 -> "Medium security"
            else -> "Strong security"
        }

    val isConfirmPasswordMatching: Boolean
        get() = confirmPassword.isNotEmpty() && confirmPassword == password
}

/**
 * User actions on the Sign Up screen.
 */
sealed interface SignUpUiAction {
    data class FullNameChanged(val value: String) : SignUpUiAction
    data class EmailChanged(val value: String) : SignUpUiAction
    data class PasswordChanged(val value: String) : SignUpUiAction
    data class ConfirmPasswordChanged(val value: String) : SignUpUiAction
    data object TogglePasswordVisibility : SignUpUiAction
    data object ToggleTermsAccepted : SignUpUiAction
    data object TermsClicked : SignUpUiAction
    data object PrivacyClicked : SignUpUiAction
    data object Submit : SignUpUiAction
    data object SignInClicked : SignUpUiAction
    data object BackClicked : SignUpUiAction
    data class SocialProviderClicked(val provider: String) : SignUpUiAction
    data object NavigationHandled : SignUpUiAction
    data object DismissFeedback : SignUpUiAction
}
