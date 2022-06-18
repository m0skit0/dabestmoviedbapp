package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.runtime.Composable

sealed class ViewState
object Loading : ViewState()
object Error: ViewState()
class Result<out T>(val value: T): ViewState()

fun <T> ViewState.resultValue(): T? = (this as? Result<T>)?.value

@Composable
inline fun <reified T> ViewState.process(onResult: @Composable (T) -> Unit) {
    when (this) {
        is Loading -> Progress()
        is Error -> Error()
        is Result<*> -> resultValue<T>()?.let { onResult(it) } ?: Error()
    }
}
