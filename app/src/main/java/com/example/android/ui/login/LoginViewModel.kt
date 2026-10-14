package com.example.android.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private val EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onAction(action: LoginUiAction) {
        when (action) {
            is LoginUiAction.EmailChanged -> {
                _uiState.update {
                    it.copy(
                        email = action.value,
                        emailError = null
                    )
                }
            }
            is LoginUiAction.PasswordChanged -> {
                _uiState.update {
                    it.copy(
                        password = action.value,
                        passwordError = null
                    )
                }
            }
            is LoginUiAction.TogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            is LoginUiAction.ToggleRememberMe -> {
                _uiState.update { it.copy(rememberMe = !it.rememberMe) }
            }
            is LoginUiAction.Submit -> {
                val current = _uiState.value
                val emailError = when {
                    current.email.isBlank() -> "Email is required"
                    !EMAIL_REGEX.matches(current.email) -> "Please enter a valid email address"
                    else -> null
                }
                val passwordError = if (current.password.isBlank()) "Password is required" else null

                if (emailError != null || passwordError != null) {
                    _uiState.update {
                        it.copy(emailError = emailError, passwordError = passwordError)
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
                            feedbackMessage = "Welcome back, Elena!"
                        )
                    }
                }
            }
            is LoginUiAction.ForgotPassword -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Password reset demo: Please use standard demo credentials")
                }
            }
            is LoginUiAction.SignUpClicked -> {
                _uiState.update { it.copy(navigateToSignUp = true) }
            }
            is LoginUiAction.SocialProviderClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "${action.provider} authentication is for UI preview only")
                }
            }
            is LoginUiAction.BiometricClicked -> {
                viewModelScope.launch {
                    _uiState.update { it.copy(isSubmitting = true) }
                    delay(500)
                    _uiState.update {
                        it.copy(
                            isSubmitting = false,
                            navigateToProfile = true,
                            feedbackMessage = "Biometric authentication verified!"
                        )
                    }
                }
            }
            is LoginUiAction.NavigationHandled -> {
                _uiState.update {
                    it.copy(navigateToProfile = false, navigateToSignUp = false)
                }
            }
            is LoginUiAction.DismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
        }
    }
}
