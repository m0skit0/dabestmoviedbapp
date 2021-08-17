package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.recyclerview.widget.RecyclerView

// Has reached bottom if we can't scroll down anymore
fun RecyclerView.hasReachedBottom(): Boolean = !canScrollVertically(1)
