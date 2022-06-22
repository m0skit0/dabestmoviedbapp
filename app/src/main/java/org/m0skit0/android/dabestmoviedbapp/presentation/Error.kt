package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.m0skit0.android.dabestmoviedbapp.R

@Preview
@Composable
fun Error(
    onTryAgain: () -> Unit = {},
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = BottomCenter,
    ) {
        Snackbar(
            action = {
                Button(onClick = onTryAgain) {
                    Text(text = stringResource(R.string.try_again))
                }
            }
        ) {
            Text(text = stringResource(R.string.error_happened))
        }
    }
}
