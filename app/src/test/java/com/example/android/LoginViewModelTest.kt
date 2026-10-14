package com.example.android

import com.example.android.ui.login.LoginUiAction
import com.example.android.ui.login.LoginViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel()
    }

    @Test
    fun `initial login state is empty and valid`() {
        val state = viewModel.uiState.value
        assertEquals("", state.email)
        assertEquals("", state.password)
        assertFalse(state.rememberMe)
        assertFalse(state.isPasswordVisible)
        assertFalse(state.isSubmitting)
        assertNull(state.emailError)
        assertNull(state.passwordError)
    }

    @Test
    fun `toggle password visibility updates state`() {
        viewModel.onAction(LoginUiAction.TogglePasswordVisibility)
        assertTrue(viewModel.uiState.value.isPasswordVisible)

        viewModel.onAction(LoginUiAction.TogglePasswordVisibility)
        assertFalse(viewModel.uiState.value.isPasswordVisible)
    }

    @Test
    fun `toggle remember me updates state`() {
        viewModel.onAction(LoginUiAction.ToggleRememberMe)
        assertTrue(viewModel.uiState.value.rememberMe)

        viewModel.onAction(LoginUiAction.ToggleRememberMe)
        assertFalse(viewModel.uiState.value.rememberMe)
    }

    @Test
    fun `submitting with empty fields shows validation errors`() {
        viewModel.onAction(LoginUiAction.Submit)

        val state = viewModel.uiState.value
        assertNotNull(state.emailError)
        assertNotNull(state.passwordError)
        assertFalse(state.isSubmitting)
    }

    @Test
    fun `sign up clicked triggers navigation to sign up`() {
        viewModel.onAction(LoginUiAction.SignUpClicked)

        assertTrue(viewModel.uiState.value.navigateToSignUp)
    }
}
