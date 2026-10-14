package com.example.android.ui

import androidx.lifecycle.ViewModel
import com.example.android.ui.navigation.NovaScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Main application ViewModel holding high-level navigation and session state.
 */
class MainViewModel : ViewModel() {

    private val _currentScreen = MutableStateFlow(NovaScreen.Login)
    val currentScreen: StateFlow<NovaScreen> = _currentScreen.asStateFlow()

    fun navigateTo(screen: NovaScreen) {
        _currentScreen.update { screen }
    }

    fun onLogout() {
        _currentScreen.update { NovaScreen.Login }
    }

    fun onLoginSuccess() {
        _currentScreen.update { NovaScreen.Profile }
    }

    fun onSignUpSuccess() {
        _currentScreen.update { NovaScreen.Profile }
    }
}
