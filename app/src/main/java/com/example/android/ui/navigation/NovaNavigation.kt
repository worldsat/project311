package com.example.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.android.ui.MainViewModel

/**
 * NovaApp entry point delegating to NavHost.
 */
@Composable
fun NovaApp(
    initialScreen: NovaScreen = NovaScreen.Login,
    modifier: Modifier = Modifier
) {
    val mainViewModel = remember {
        MainViewModel().apply {
            navigateTo(initialScreen)
        }
    }

    NavHost(
        mainViewModel = mainViewModel,
        modifier = modifier
    )
}
