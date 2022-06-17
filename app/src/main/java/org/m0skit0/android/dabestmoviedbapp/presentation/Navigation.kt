package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailItem
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList

private const val ROUTE_TOP_TV_SHOWS = "topShows"
private const val ROUTE_TV_SHOW_DETAILS = "tvShowDetail/{id}"
private const val ROUTE_TV_SHOW_DETAILS_ID = "id"
private const val ROUTE_SIMILAR_TV_SHOWS = "similarTvShows/{id}"
private const val ROUTE_SIMILAR_TV_SHOWS_ID = "id"

private fun NavHostController.navigateToDetailsProvider(): (Long) -> Unit = { id -> navigate("tvShowDetail/$id") }

@ExperimentalCoilApi
@Composable
fun NavHostController.SetupNavHost() {
    NavHost(navController = this, startDestination = ROUTE_TOP_TV_SHOWS) {
        composable(ROUTE_TOP_TV_SHOWS) {
            TopShowList(
                navigateToDetailsProvider()
            )
        }
        composable(
            route = ROUTE_TV_SHOW_DETAILS,
            arguments = listOf(navArgument(ROUTE_TV_SHOW_DETAILS_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getLong(ROUTE_TV_SHOW_DETAILS_ID) ?: 0
            TVShowDetailItem(tvShowId)
        }
        composable(
            route = ROUTE_SIMILAR_TV_SHOWS,
            arguments = listOf(navArgument(ROUTE_SIMILAR_TV_SHOWS_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getLong(ROUTE_SIMILAR_TV_SHOWS_ID) ?: 0
            TVShowDetailItem(tvShowId)
        }
    }
}
