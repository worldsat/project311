package com.example.android.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.data.mock.MockProfileData
import com.example.android.data.model.ProfileDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the Profile screen managing UI state and action reduction.
 */
class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProfileUiState(
            profile = MockProfileData.novaPreviewProfile,
            securityAndBiometricsEnabled = true,
            materialYouThemeEnabled = true,
            selectedDestination = ProfileDestination.Profile
        )
    )
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onAction(action: ProfileUiAction) {
        when (action) {
            is ProfileUiAction.SecurityPreferenceChanged -> {
                _uiState.update { it.copy(securityAndBiometricsEnabled = action.enabled) }
            }
            is ProfileUiAction.MaterialYouPreferenceChanged -> {
                _uiState.update { it.copy(materialYouThemeEnabled = action.enabled) }
            }
            is ProfileUiAction.BottomDestinationClicked -> {
                _uiState.update {
                    it.copy(
                        selectedDestination = action.destination,
                        feedbackMessage = if (action.destination != ProfileDestination.Profile) {
                            "${action.destination.label} section demo"
                        } else null
                    )
                }
            }
            is ProfileUiAction.BackClicked -> {
                _uiState.update { it.copy(navigateToLogin = true) }
            }
            is ProfileUiAction.EditProfileClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Edit Profile requested") }
            }
            is ProfileUiAction.SettingsClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Settings requested") }
            }
            is ProfileUiAction.ShareProfileClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Sharing profile @${it.profile.handle}") }
            }
            is ProfileUiAction.PersonalInformationClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Personal Information: ${it.profile.displayName}") }
            }
            is ProfileUiAction.PaymentMethodsClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Payment Methods: Visa ending in ${it.profile.paymentCardLastFour}") }
            }
            is ProfileUiAction.PushNotificationsClicked -> {
                _uiState.update { it.copy(feedbackMessage = "Push Notifications settings") }
            }
            is ProfileUiAction.StorageClicked -> {
                _uiState.update {
                    it.copy(feedbackMessage = "Cloud Storage: ${it.profile.storageUsedGb} GB used")
                }
            }
            is ProfileUiAction.LogOutClicked -> {
                _uiState.update {
                    it.copy(
                        isLoggingOut = true,
                        navigateToLogin = true,
                        feedbackMessage = "Logged out of Nova session"
                    )
                }
            }
            is ProfileUiAction.NavigationHandled -> {
                _uiState.update { it.copy(navigateToLogin = false) }
            }
            is ProfileUiAction.DismissFeedback -> {
                _uiState.update { it.copy(feedbackMessage = null) }
            }
        }
    }
}
