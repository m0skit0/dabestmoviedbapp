package org.m0skit0.android.dabestmoviedbapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Hello")
        }
    }
}
