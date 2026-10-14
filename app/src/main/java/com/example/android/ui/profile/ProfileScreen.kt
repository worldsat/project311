package com.example.android.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.data.mock.MockProfileData
import com.example.android.ui.components.NovaAccountSecuritySection
import com.example.android.ui.components.NovaActionButtons
import com.example.android.ui.components.NovaAppPreferencesSection
import com.example.android.ui.components.NovaBottomNavigationBar
import com.example.android.ui.components.NovaLogoutFooter
import com.example.android.ui.components.NovaProfileHero
import com.example.android.ui.components.NovaStatsCard
import com.example.android.ui.components.NovaTopAppBar
import com.example.android.ui.theme.NovaSpacing
import com.example.android.ui.theme.NovaTheme

/**
 * Route composable connecting ProfileViewModel with ProfileScreen.
 */
@Composable
fun ProfileRoute(
    viewModel: ProfileViewModel,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.navigateToLogin) {
        if (uiState.navigateToLogin) {
            viewModel.onAction(ProfileUiAction.NavigationHandled)
            onNavigateToLogin()
        }
    }

    // Display one-shot feedback messages
    LaunchedEffect(uiState.feedbackMessage) {
        uiState.feedbackMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onAction(ProfileUiAction.DismissFeedback)
        }
    }

    ProfileScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

/**
 * Stateless ProfileScreen rendering the full Nova user profile hierarchy.
 */
@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onAction: (ProfileUiAction) -> Unit,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            NovaTopAppBar(
                onBackClick = { onAction(ProfileUiAction.BackClicked) },
                onEditClick = { onAction(ProfileUiAction.EditProfileClicked) },
                onSettingsClick = { onAction(ProfileUiAction.SettingsClicked) },
                onAvatarClick = { onAction(ProfileUiAction.EditProfileClicked) },
                modifier = Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            NovaBottomNavigationBar(
                currentDestination = uiState.selectedDestination,
                onDestinationSelect = { destination ->
                    onAction(ProfileUiAction.BottomDestinationClicked(destination))
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = NovaSpacing.md, vertical = NovaSpacing.xs),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(NovaSpacing.lg)
        ) {
            // Profile Hero (Avatar, Name, Handle, Role, Pro Member Chip)
            NovaProfileHero(
                profile = uiState.profile,
                modifier = Modifier.padding(top = NovaSpacing.xs)
            )

            // Quick Stats Card (Projects, Followers, Rating)
            NovaStatsCard(
                profile = uiState.profile
            )

            // Action Buttons (Edit Profile + Share)
            NovaActionButtons(
                onEditProfileClick = { onAction(ProfileUiAction.EditProfileClicked) },
                onShareClick = { onAction(ProfileUiAction.ShareProfileClicked) }
            )

            // Section 1: Account & Security
            NovaAccountSecuritySection(
                profile = uiState.profile,
                securityEnabled = uiState.securityAndBiometricsEnabled,
                onSecurityToggle = { onAction(ProfileUiAction.SecurityPreferenceChanged(it)) },
                onPersonalInfoClick = { onAction(ProfileUiAction.PersonalInformationClicked) },
                onPaymentMethodsClick = { onAction(ProfileUiAction.PaymentMethodsClicked) }
            )

            // Section 2: App Preferences
            NovaAppPreferencesSection(
                profile = uiState.profile,
                materialYouEnabled = uiState.materialYouThemeEnabled,
                onMaterialYouToggle = { onAction(ProfileUiAction.MaterialYouPreferenceChanged(it)) },
                onNotificationsClick = { onAction(ProfileUiAction.PushNotificationsClicked) },
                onStorageClick = { onAction(ProfileUiAction.StorageClicked) }
            )

            // Logout Button & App Version Footer
            NovaLogoutFooter(
                onLogoutClick = { onAction(ProfileUiAction.LogOutClicked) },
                modifier = Modifier.padding(top = NovaSpacing.xs)
            )

            Spacer(modifier = Modifier.height(NovaSpacing.md))
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun ProfileScreenPreview() {
    NovaTheme {
        ProfileScreen(
            uiState = ProfileUiState(profile = MockProfileData.novaPreviewProfile),
            onAction = {}
        )
    }
}
