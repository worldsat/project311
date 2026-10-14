package com.example.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.android.data.model.ProfileDestination
import com.example.android.ui.theme.NovaPrimary

/**
 * Fixed Bottom Navigation Bar matching Nova Material 3 specifications.
 * Renders Home, Discover, Messages, and Profile with an active pill indicator.
 */
@Composable
fun NovaBottomNavigationBar(
    currentDestination: ProfileDestination,
    onDestinationSelect: (ProfileDestination) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(72.dp)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileDestination.entries.forEach { destination ->
                val isSelected = destination == currentDestination
                val iconRes = when (destination) {
                    ProfileDestination.Home -> R.drawable.ic_home
                    ProfileDestination.Discover -> R.drawable.ic_explore
                    ProfileDestination.Messages -> R.drawable.ic_chat_bubble
                    ProfileDestination.Profile -> R.drawable.ic_person
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onDestinationSelect(destination) }
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Pill Indicator Box when selected
                    Box(
                        modifier = Modifier
                            .height(32.dp)
                            .clip(CircleShape)
                            .then(
                                if (isSelected) {
                                    Modifier
                                        .background(NovaPrimary)
                                        .padding(horizontal = 20.dp)
                                } else {
                                    Modifier.padding(horizontal = 20.dp)
                                }
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = destination.label,
                            tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = destination.label,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            fontSize = 11.sp
                        ),
                        color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
