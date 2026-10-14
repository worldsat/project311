package com.example.android.ui.profile

import com.example.android.data.mock.MockProfileData
import com.example.android.data.model.DemoProfile
import com.example.android.data.model.ProfileDestination

/**
 * Immutable UI State for the Profile Screen.
 */
data class ProfileUiState(
    val profile: DemoProfile = MockProfileData.novaPreviewProfile,
    val securityAndBiometricsEnabled: Boolean = true,
    val materialYouThemeEnabled: Boolean = true,
    val selectedDestination: ProfileDestination = ProfileDestination.Profile,
    val isLoggingOut: Boolean = false,
    val feedbackMessage: String? = null,
    val navigateToLogin: Boolean = false
)

/**
 * Sealed interface representing all user actions on the Profile Screen.
 */
sealed interface ProfileUiAction {
    data object BackClicked : ProfileUiAction
    data object EditProfileClicked : ProfileUiAction
    data object SettingsClicked : ProfileUiAction
    data object ShareProfileClicked : ProfileUiAction
    data class SecurityPreferenceChanged(val enabled: Boolean) : ProfileUiAction
    data class MaterialYouPreferenceChanged(val enabled: Boolean) : ProfileUiAction
    data object PersonalInformationClicked : ProfileUiAction
    data object PaymentMethodsClicked : ProfileUiAction
    data object PushNotificationsClicked : ProfileUiAction
    data object StorageClicked : ProfileUiAction
    data class BottomDestinationClicked(val destination: ProfileDestination) : ProfileUiAction
    data object LogOutClicked : ProfileUiAction
    data object NavigationHandled : ProfileUiAction
    data object DismissFeedback : ProfileUiAction
}
