package org.m0skit0.android.dabestmoviedbapp.presentation.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import org.m0skit0.android.dabestmoviedbapp.R

fun Fragment.toast(@StringRes id: Int) {
    Toast.makeText(activity, id, Toast.LENGTH_LONG).show()
}

fun Fragment.errorToast() {
    toast(R.string.error_happened)
}
