package com.example.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.R
import com.example.android.data.model.DemoProfile
import com.example.android.ui.theme.NovaCustomShapes
import com.example.android.ui.theme.NovaSpacing
import com.example.android.ui.theme.NovaSwitchTrack

/**
 * Account & Security settings section with navigation and switch rows.
 */
@Composable
fun NovaAccountSecuritySection(
    profile: DemoProfile,
    securityEnabled: Boolean,
    onSecurityToggle: (Boolean) -> Unit,
    onPersonalInfoClick: () -> Unit,
    onPaymentMethodsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Account & Security",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = NovaSpacing.xs, vertical = NovaSpacing.xs)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(NovaCustomShapes.SettingsCard)
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            // Row 1: Personal Information
            SettingsNavigationRow(
                iconRes = R.drawable.ic_account_circle,
                title = "Personal Information",
                subtitle = "Name, email, contact details",
                onClick = onPersonalInfoClick
            )

            // Row 2: Security & Biometrics
            SettingsSwitchRow(
                iconRes = R.drawable.ic_shield,
                title = "Security & Biometrics",
                subtitle = if (securityEnabled) "Two-factor authentication enabled" else "Two-factor authentication disabled",
                isChecked = securityEnabled,
                onCheckedChange = onSecurityToggle
            )

            // Row 3: Payment Methods
            SettingsNavigationRow(
                iconRes = R.drawable.ic_credit_card,
                title = "Payment Methods",
                subtitle = "Visa ending in ${profile.paymentCardLastFour ?: "4289"}",
                onClick = onPaymentMethodsClick
            )
        }
    }
}

/**
 * App Preferences settings section with theme toggle, notifications, and cloud storage progress gauge.
 */
@Composable
fun NovaAppPreferencesSection(
    profile: DemoProfile,
    materialYouEnabled: Boolean,
    onMaterialYouToggle: (Boolean) -> Unit,
    onNotificationsClick: () -> Unit,
    onStorageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "App Preferences",
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = NovaSpacing.xs, vertical = NovaSpacing.xs)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(NovaCustomShapes.SettingsCard)
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            // Row 1: Material You Theme
            SettingsSwitchRow(
                iconRes = R.drawable.ic_palette,
                title = "Material You Theme",
                subtitle = "Dynamic color palette adaptation",
                isChecked = materialYouEnabled,
                onCheckedChange = onMaterialYouToggle
            )

            // Row 2: Push Notifications
            SettingsNavigationRow(
                iconRes = R.drawable.ic_notifications,
                title = "Push Notifications",
                subtitle = "Activity, messages, and mentions",
                onClick = onNotificationsClick
            )

            // Row 3: Data & Cloud Storage with Progress Bar
            SettingsStorageRow(
                profile = profile,
                onClick = onStorageClick
            )
        }
    }
}

@Composable
private fun SettingsNavigationRow(
    iconRes: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Leading Circular Icon Box
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // Title and Subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Trailing Chevron
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun SettingsSwitchRow(
    iconRes: Int,
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!isChecked) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Leading Circular Icon Box
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // Title and Subtitle
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Material 3 Switch with checkmark thumb matching spec
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            thumbContent = if (isChecked) {
                {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            } else null,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = NovaSwitchTrack,
                uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )
    }
}

@Composable
private fun SettingsStorageRow(
    profile: DemoProfile,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Circular Icon Box
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceContainerHighest),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_cloud),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            // Title and Subtitle
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Data & Cloud Storage",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "${profile.storageUsedGb} GB of ${profile.storageCapacityGb.toInt()} GB used",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Percentage Text Badge
            Text(
                text = "${profile.storagePercentage}%",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Linear Progress Bar indented to align with text
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.width(54.dp))

            LinearProgressIndicator(
                progress = { profile.storageProgressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        }
    }
}
