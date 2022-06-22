package org.m0skit0.android.dabestmoviedbapp.presentation

import android.content.res.Configuration
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun Orientation(
    onLandscape: @Composable () -> Unit,
    onPortrait: @Composable () -> Unit,
) {
    LocalConfiguration.current.run {
        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> onLandscape()
            Configuration.ORIENTATION_PORTRAIT -> onPortrait()
            else -> Log.d("TVShowDetailItem", "UNKNOWN")
        }
    }
}
