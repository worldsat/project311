package com.example.android.data.mock

import com.example.android.R
import com.example.android.data.model.DemoProfile

/**
 * Seeded mock data strictly matching the Nova User Profile reference (Section 24).
 */
object MockProfileData {
    val novaPreviewProfile = DemoProfile(
        id = "nova-demo-profile",
        displayName = "Elena Vance",
        handle = "@elenavance",
        role = "Senior Product Designer",
        isProMember = true,
        isVerified = true,
        isOnline = true,
        projectsCount = 42,
        followersLabel = "12.8k",
        rating = 4.9,
        paymentCardLastFour = "4289",
        storageUsedGb = 18.4,
        storageCapacityGb = 50.0,
        avatarResource = R.drawable.avatar_elena,
        logoResource = R.drawable.nova_brand_logo
    )
}
