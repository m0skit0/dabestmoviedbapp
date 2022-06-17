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

private fun NavHostController.navigateToDetails(): (Long) -> Unit = { id -> navigate("tvShowDetail/$id") }

@ExperimentalCoilApi
@Composable
fun NavHostController.SetupNavHost() {
    NavHost(navController = this, startDestination = "topShows") {
        composable("topShows") {
            TopShowList(
                navigateToDetails()
            )
        }
        composable(
            route = "tvShowDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })
        ) { backStackEntry ->
            val tvShowId = backStackEntry.arguments?.getLong("id") ?: 0
            TVShowDetailItem(tvShowId)
        }
    }
}
