package org.m0skit0.android.dabestmoviedbapp.presentation

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes id: Int) {
    Toast.makeText(activity, id, Toast.LENGTH_LONG).show()
}
