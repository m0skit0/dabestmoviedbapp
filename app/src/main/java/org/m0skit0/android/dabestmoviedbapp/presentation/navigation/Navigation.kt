package org.m0skit0.android.dabestmoviedbapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi

private const val ROUTE_TOP_TV_SHOWS = "topShows"

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun NavHostController.SetupNavHost() {
    NavHost(navController = this, startDestination = ROUTE_TOP_TV_SHOWS) {
        composable(ROUTE_TOP_TV_SHOWS) {
            TODO()
        }
    }
}
