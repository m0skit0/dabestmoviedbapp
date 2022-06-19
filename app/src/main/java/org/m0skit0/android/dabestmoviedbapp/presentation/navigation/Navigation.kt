package org.m0skit0.android.dabestmoviedbapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailItem
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList

private const val ROUTE_TOP_TV_SHOWS = "topShows"
private const val ROUTE_DETAIL_TV_SHOW_BASE = "tvShowDetail"
private const val ROUTE_DETAIL_TV_SHOW_ID = "id"
private const val ROUTE_DETAIL_TV_SHOW = "$ROUTE_DETAIL_TV_SHOW_BASE/{$ROUTE_DETAIL_TV_SHOW_ID}"

private fun NavHostController.navigateToDetailsProvider(): (Long) -> Unit = { id -> navigate("$ROUTE_DETAIL_TV_SHOW_BASE/$id") }

@ExperimentalPagerApi
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
            route = ROUTE_DETAIL_TV_SHOW,
            arguments = listOf(navArgument(ROUTE_DETAIL_TV_SHOW_ID) { type = NavType.LongType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getLong(ROUTE_DETAIL_TV_SHOW_ID) ?: 0
            TVShowDetailItem(tvShowId)
        }
    }
}
