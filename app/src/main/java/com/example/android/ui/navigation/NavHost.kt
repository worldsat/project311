package com.example.android.ui.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.android.ui.MainViewModel
import com.example.android.ui.login.LoginRoute
import com.example.android.ui.login.LoginViewModel
import com.example.android.ui.profile.ProfileRoute
import com.example.android.ui.profile.ProfileViewModel
import com.example.android.ui.signup.SignUpRoute
import com.example.android.ui.signup.SignUpViewModel

/**
 * Root Navigation Host coordinating transitions between Login, SignUp, and Profile.
 */
@Composable
fun NavHost(
    mainViewModel: MainViewModel = remember { MainViewModel() },
    loginViewModel: LoginViewModel = remember { LoginViewModel() },
    signUpViewModel: SignUpViewModel = remember { SignUpViewModel() },
    profileViewModel: ProfileViewModel = remember { ProfileViewModel() },
    modifier: Modifier = Modifier
) {
    val currentScreen by mainViewModel.currentScreen.collectAsState()

    // Back handling: navigates back to Login unless already at Login
    BackHandler(enabled = currentScreen != NovaScreen.Login) {
        when (currentScreen) {
            NovaScreen.SignUp -> mainViewModel.navigateTo(NovaScreen.Login)
            NovaScreen.Profile -> mainViewModel.navigateTo(NovaScreen.Login)
            NovaScreen.Login -> { /* Handled by OS */ }
        }
    }

    Crossfade(
        targetState = currentScreen,
        animationSpec = tween(300),
        label = "NovaScreenTransition",
        modifier = modifier
    ) { screen ->
        when (screen) {
            NovaScreen.Login -> {
                LoginRoute(
                    viewModel = loginViewModel,
                    onNavigateToSignUp = { mainViewModel.navigateTo(NovaScreen.SignUp) },
                    onNavigateToProfile = { mainViewModel.navigateTo(NovaScreen.Profile) }
                )
            }
            NovaScreen.SignUp -> {
                SignUpRoute(
                    viewModel = signUpViewModel,
                    onNavigateToLogin = { mainViewModel.navigateTo(NovaScreen.Login) },
                    onNavigateToProfile = { mainViewModel.navigateTo(NovaScreen.Profile) }
                )
            }
            NovaScreen.Profile -> {
                ProfileRoute(
                    viewModel = profileViewModel,
                    onNavigateToLogin = { mainViewModel.navigateTo(NovaScreen.Login) }
                )
            }
        }
    }
}
