package org.m0skit0.android.dabestmoviedbapp.presentation

import android.view.View

fun View.visible(): View = apply {
    visibility = View.VISIBLE
}

fun View.gone(): View = apply {
    visibility = View.GONE
}

fun View.invisible(): View = apply {
    visibility = View.INVISIBLE
}
