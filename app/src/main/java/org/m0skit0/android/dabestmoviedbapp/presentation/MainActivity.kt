package org.m0skit0.android.dabestmoviedbapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import coil.annotation.ExperimentalCoilApi
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList

@ExperimentalCoilApi
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopShowList()
        }
    }
}
