package org.m0skit0.android.dabestmoviedbapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.m0skit0.android.dabestmoviedbapp.presentation.navigation.SetupNavHost

@ExperimentalPagerApi
@ExperimentalCoilApi
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            rememberNavController().SetupNavHost()
        }
    }
}
