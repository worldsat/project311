package com.example.android.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

class SignUpViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun onAction(action: SignUpUiAction) {
        when (action) {
            is SignUpUiAction.FullNameChanged -> {
                _uiState.update { it.copy(fullName = action.value, nameError = null) }
            }
            is SignUpUiAction.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = action.value,
                        emailError = null
                    )
                }
            }
            is SignUpUiAction.PasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = action.value,
                        passwordError = null,
                        confirmPasswordError = if (it.confirmPassword.isNotEmpty() && it.confirmPassword != action.value) {
                            "Passwords do not match"
                        } else null
                    )
                }
            }
            is SignUpUiAction.ConfirmPasswordChanged -> {
                _uiState.update {
                    it.copy(
                        confirmPassword = action.value,
                        confirmPasswordError = if (action.value.isNotEmpty() && action.value != it.password) {
                            "Passwords do not match"
                        } else null
                    )
                }
            }
            is SignUpUiAction.TogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is SignUpUiAction.ToggleTermsAccepted -> {
                _uiState.update { it.copy(acceptedTerms = !it.acceptedTerms, termsError = null) }
            }
            is SignUpUiAction.TermsClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Terms of Service: Nova Demo Workspace terms") }
            }
            is SignUpUiAction.PrivacyClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Privacy Policy: Local demo privacy terms") }
            }
            is SignUpUiAction.SignInClicked, is SignUpUiAction.BackClicked -> {
                _uiState.update { it.copy(navigateToLogin = true) }
            }
            is SignUpUiAction.SocialProviderClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Registering with ${action.provider} is for UI preview only")
                }
            }
            is SignUpUiAction.Submit -> {
                val current = _uiState.value
                val nameError = if (current.fullName.isBlank()) "Full name is required" else null
                val emailError = when {
                    current.email.isBlank() -> "Email is required"
                    !EMAIL_REGEX.matches(current.email) -> "Please enter a valid email address"
                    else -> null
                }
                val passwordError = when {
                    current.password.isBlank() -> "Password is required"
                    current.password.length < 8 -> "Password must be at least 8 characters"
                    else -> null
                }
                val confirmError = when {
                    current.confirmPassword.isBlank() -> "Please confirm your password"
                    current.confirmPassword != current.password -> "Passwords do not match"
                    else -> null
                }
                val termsError = if (!current.acceptedTerms) "You must accept the terms to proceed" else null

                if (nameError != null || emailError != null || passwordError != null || confirmError != null || termsError != null) {
                    _uiState.update {
                        it.copy(
                            nameError = nameError,
                            emailError = emailError,
                            passwordError = passwordError,
                            confirmPasswordError = confirmError,
                            termsError = termsError
                        )
                    }
                    return
                }

                viewModelScope.launch {
                    _uiState.update { it.copy(isSubmitting = true) }
                    delay(800)
                    _uiState.update {
                        it.copy(
                            isSubmitting = false,
                            navigateToProfile = true,
                            feedbackMessage = "Account created successfully for ${it.fullName}!"
                        )
                    }
                }
            }
            is SignUpUiAction.NavigationHandled -> {
                _uiState.update { it.copy(navigateToProfile = false, navigateToLogin = false) }
            }
            is SignUpUiAction.DismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
        }
    }
}
