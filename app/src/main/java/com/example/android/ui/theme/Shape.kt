package com.example.android.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ==========================================
// Official Nova Shape System
// Defined in Design.md and rules.md
// ==========================================

val NovaShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

object NovaCustomShapes {
    val Full = CircleShape
    val ButtonPill = RoundedCornerShape(100.dp)
    val InputField = RoundedCornerShape(20.dp)
    val StatsCard = RoundedCornerShape(24.dp)
    val SettingsCard = RoundedCornerShape(16.dp)
    val ChipPill = RoundedCornerShape(100.dp)
}
