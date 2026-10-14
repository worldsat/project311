package com.example.android

import com.example.android.ui.signup.SignUpUiAction
import com.example.android.ui.signup.SignUpViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SignUpViewModelTest {

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun setUp() {
        viewModel = SignUpViewModel()
    }

    @Test
    fun `initial signup state has empty fields and zero strength`() {
        val state = viewModel.uiState.value
        assertEquals("", state.fullName)
        assertEquals("", state.email)
        assertEquals("", state.password)
        assertEquals("", state.confirmPassword)
        assertFalse(state.acceptedTerms)
        assertEquals(0, state.passwordStrengthLevel)
        assertFalse(state.isConfirmPasswordMatching)
    }

    @Test
    fun `password strength increases with password length`() {
        viewModel.onAction(SignUpUiAction.PasswordChanged("abc"))
        assertEquals(1, viewModel.uiState.value.passwordStrengthLevel)

        viewModel.onAction(SignUpUiAction.PasswordChanged("abcdef"))
        assertEquals(2, viewModel.uiState.value.passwordStrengthLevel)

        viewModel.onAction(SignUpUiAction.PasswordChanged("abcdef12"))
        assertEquals(3, viewModel.uiState.value.passwordStrengthLevel)
        assertEquals("Medium security", viewModel.uiState.value.passwordStrengthLabel)

        viewModel.onAction(SignUpUiAction.PasswordChanged("abcdef123456"))
        assertEquals(4, viewModel.uiState.value.passwordStrengthLevel)
        assertEquals("Strong security", viewModel.uiState.value.passwordStrengthLabel)
    }

    @Test
    fun `confirm password match indicator updates correctly`() {
        viewModel.onAction(SignUpUiAction.PasswordChanged("secret123"))
        viewModel.onAction(SignUpUiAction.ConfirmPasswordChanged("secret"))
        assertFalse(viewModel.uiState.value.isConfirmPasswordMatching)

        viewModel.onAction(SignUpUiAction.ConfirmPasswordChanged("secret123"))
        assertTrue(viewModel.uiState.value.isConfirmPasswordMatching)
    }

    @Test
    fun `submit without accepted terms produces validation error`() {
        viewModel.onAction(SignUpUiAction.FullNameChanged("Alex Morgan"))
        viewModel.onAction(SignUpUiAction.EmailChanged("alex.morgan@domain.com"))
        viewModel.onAction(SignUpUiAction.PasswordChanged("password123"))
        viewModel.onAction(SignUpUiAction.ConfirmPasswordChanged("password123"))

        viewModel.onAction(SignUpUiAction.Submit)

        val state = viewModel.uiState.value
        assertNotNull(state.termsError)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun `navigation back to sign in works`() {
        viewModel.onAction(SignUpUiAction.SignInClicked)
        assertTrue(viewModel.uiState.value.navigateToLogin)
    }
}
