package org.m0skit0.android.dabestmoviedbapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.m0skit0.android.dabestmoviedbapp.di.getSimilarTVShowsComposable
import org.m0skit0.android.dabestmoviedbapp.di.getTopShowListComposable

private const val ROUTE_TOP_TV_SHOWS = "topShows"
private const val ROUTE_SIMILAR_TV_SHOWS_BASE = "similarTvShows"
private const val ROUTE_SIMILAR_TV_SHOWS_ID = "id"
private const val ROUTE_SIMILAR_TV_SHOWS = "$ROUTE_SIMILAR_TV_SHOWS_BASE/{$ROUTE_SIMILAR_TV_SHOWS_ID}"

private fun NavHostController.navigateToDetailsProvider(): (Long) -> Unit = { id -> navigate("$ROUTE_SIMILAR_TV_SHOWS_BASE/$id") }

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun NavHostController.SetupNavHost() {
    NavHost(navController = this, startDestination = ROUTE_TOP_TV_SHOWS) {
        composable(ROUTE_TOP_TV_SHOWS) {
            getTopShowListComposable(navigateToDetailsProvider())()
        }
        composable(
            route = ROUTE_SIMILAR_TV_SHOWS,
            arguments = listOf(navArgument(ROUTE_SIMILAR_TV_SHOWS_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getLong(ROUTE_SIMILAR_TV_SHOWS_ID) ?: 0
            getSimilarTVShowsComposable(tvShowId)()
        }
    }
}
