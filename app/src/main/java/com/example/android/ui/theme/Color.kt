package com.example.android.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// ==========================================
// Official Nova Material 3 Color Tokens
// Defined in Design.md and rules.md
// ==========================================

val NovaPrimary = Color(0xFF2514A5)
val NovaOnPrimary = Color(0xFFFFFFFF)
val NovaPrimaryContainer = Color(0xFFE0E0FF)
val NovaOnPrimaryContainer = Color(0xFF08006C)

val NovaSecondary = Color(0xFF4F46E5)
val NovaOnSecondary = Color(0xFFFFFFFF)
val NovaSecondaryContainer = Color(0xFFDC2C4F)
val NovaOnSecondaryContainer = Color(0xFFFFFFFF)

val NovaSurface = Color(0xFFFCF8FF)
val NovaOnSurface = Color(0xFF1A1B2E)
val NovaOnSurfaceVariant = Color(0xFF5B5D72)
val NovaSurfaceVariant = Color(0xFFEDE8F8)

val NovaSurfaceContainerLowest = Color(0xFFFFFFFF)
val NovaSurfaceContainerLow = Color(0xFFF6F2FF)
val NovaSurfaceContainer = Color(0xFFF1ECFB)
val NovaSurfaceContainerHigh = Color(0xFFE8E2F5)
val NovaSurfaceContainerHighest = Color(0xFFE3DFFF)

val NovaOutline = Color(0xFFD6D2E6)
val NovaOutlineVariant = Color(0xFFEAE6F5)

val NovaError = Color(0xFFBA1A1A)
val NovaErrorContainer = Color(0xFFFFDAD6)
val NovaOnErrorContainer = Color(0xFF410002)

// ==========================================
// Centralized Decorative & Component Accents
// ==========================================
val NovaStatusOnline = Color(0xFFDC2C4F)
val NovaRatingStar = Color(0xFFB90538)
val NovaEditButton = Color(0xFF372CBF)
val NovaSwitchTrack = Color(0xFF2514A5)

/**
 * Baseline Light Color Scheme for Nova Workspace
 */
val NovaLightColorScheme: ColorScheme = lightColorScheme(
    primary = NovaPrimary,
    onPrimary = NovaOnPrimary,
    primaryContainer = NovaPrimaryContainer,
    onPrimaryContainer = NovaOnPrimaryContainer,
    secondary = NovaSecondary,
    onSecondary = NovaOnSecondary,
    secondaryContainer = NovaSecondaryContainer,
    onSecondaryContainer = NovaOnSecondaryContainer,
    background = NovaSurface,
    onBackground = NovaOnSurface,
    surface = NovaSurface,
    onSurface = NovaOnSurface,
    surfaceVariant = NovaSurfaceVariant,
    onSurfaceVariant = NovaOnSurfaceVariant,
    outline = NovaOutline,
    outlineVariant = NovaOutlineVariant,
    error = NovaError,
    errorContainer = NovaErrorContainer,
    onError = Color.White,
    onErrorContainer = NovaOnErrorContainer,
    surfaceContainerLowest = NovaSurfaceContainerLowest,
    surfaceContainerLow = NovaSurfaceContainerLow,
    surfaceContainer = NovaSurfaceContainer,
    surfaceContainerHigh = NovaSurfaceContainerHigh,
    surfaceContainerHighest = NovaSurfaceContainerHighest
)