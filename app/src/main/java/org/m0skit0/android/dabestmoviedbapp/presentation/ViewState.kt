package org.m0skit0.android.dabestmoviedbapp.presentation

sealed class ViewState
object Loading : ViewState()
object Error: ViewState()
class Result<out T>(val data: T): ViewState()
