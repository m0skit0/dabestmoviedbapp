package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed class ViewState
object Loading: ViewState()
object Failure: ViewState()
object Success: ViewState()

data class StateResult<out T>(val viewState: ViewState, val value: T)

@Composable
fun <T> StateResult<T>.Process(onResult: @Composable (T, Any) -> Unit) {
    when (viewState) {
        is Loading -> Box(modifier = Modifier.fillMaxSize()) {
            Progress()
            onResult(value, this)
        }
        is Failure -> Column(modifier = Modifier.fillMaxSize()) {
            onResult(value, this)
            Error()
        }
        is Success -> onResult(value, Unit)
    }
}
