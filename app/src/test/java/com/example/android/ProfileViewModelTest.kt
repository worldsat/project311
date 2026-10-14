package com.example.android

import com.example.android.data.model.ProfileDestination
import com.example.android.ui.profile.ProfileUiAction
import com.example.android.ui.profile.ProfileViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Unit tests for ProfileViewModel verifying deterministic seed values and action reductions.
 */
class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setUp() {
        viewModel = ProfileViewModel()
    }

    @Test
    fun `initial profile state matches Nova specification`() {
        val state = viewModel.uiState.value

        assertEquals("Elena Vance", state.profile.displayName)
        assertEquals("@elenavance", state.profile.handle)
        assertEquals("Senior Product Designer", state.profile.role)
        assertTrue(state.profile.isProMember)
        assertTrue(state.profile.isVerified)
        assertTrue(state.profile.isOnline)
        assertEquals(42, state.profile.projectsCount)
        assertEquals("12.8k", state.profile.followersLabel)
        assertEquals(4.9, state.profile.rating, 0.01)
        assertEquals("4289", state.profile.paymentCardLastFour)
        assertEquals(37, state.profile.storagePercentage)
        assertTrue(state.securityAndBiometricsEnabled)
        assertTrue(state.materialYouThemeEnabled)
        assertEquals(ProfileDestination.Profile, state.selectedDestination)
    }

    @Test
    fun `toggling security switch updates state independently`() {
        viewModel.onAction(ProfileUiAction.SecurityPreferenceChanged(false))

        assertFalse(viewModel.uiState.value.securityAndBiometricsEnabled)
        assertTrue(viewModel.uiState.value.materialYouThemeEnabled)

        viewModel.onAction(ProfileUiAction.SecurityPreferenceChanged(true))
        assertTrue(viewModel.uiState.value.securityAndBiometricsEnabled)
    }

    @Test
    fun `toggling material you switch updates state independently`() {
        viewModel.onAction(ProfileUiAction.MaterialYouPreferenceChanged(false))

        assertFalse(viewModel.uiState.value.materialYouThemeEnabled)
        assertTrue(viewModel.uiState.value.securityAndBiometricsEnabled)
    }

    @Test
    fun `selecting bottom destination updates selection`() {
        viewModel.onAction(ProfileUiAction.BottomDestinationClicked(ProfileDestination.Discover))

        assertEquals(ProfileDestination.Discover, viewModel.uiState.value.selectedDestination)
        assertEquals("Discover section demo", viewModel.uiState.value.feedbackMessage)
    }

    @Test
    fun `logout action updates logout state`() {
        viewModel.onAction(ProfileUiAction.LogOutClicked)

        assertTrue(viewModel.uiState.value.isLoggingOut)
        assertEquals("Logged out of Nova session", viewModel.uiState.value.feedbackMessage)
    }
}
