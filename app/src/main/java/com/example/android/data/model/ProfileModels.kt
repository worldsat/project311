package com.example.android.data.model

import androidx.annotation.DrawableRes
import kotlin.math.roundToInt

/**
 * Domain model representing the user's profile information.
 * Follows Nova specification Section 24.
 */
data class DemoProfile(
    val id: String,
    val displayName: String,
    val handle: String,
    val role: String,
    val isProMember: Boolean,
    val isVerified: Boolean = true,
    val isOnline: Boolean = true,
    val projectsCount: Int,
    val followersLabel: String,
    val rating: Double,
    val paymentCardLastFour: String?,
    val storageUsedGb: Double,
    val storageCapacityGb: Double,
    @field:DrawableRes val avatarResource: Int?,
    @field:DrawableRes val logoResource: Int?
) {
    val storagePercentage: Int
        get() = ((storageUsedGb / storageCapacityGb) * 100).roundToInt()

    val storageProgressFraction: Float
        get() = (storageUsedGb / storageCapacityGb).toFloat().coerceIn(0f, 1f)
}

/**
 * Supported navigation destinations in the bottom navigation bar.
 */
enum class ProfileDestination(val label: String) {
    Home("Home"),
    Discover("Discover"),
    Messages("Messages"),
    Profile("Profile")
}
