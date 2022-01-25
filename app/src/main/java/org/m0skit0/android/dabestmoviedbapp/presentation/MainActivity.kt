package org.m0skit0.android.dabestmoviedbapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.m0skit0.android.dabestmoviedbapp.di.NAMED_TOP_TV_SHOWS_USE_CASE
import org.m0skit0.android.dabestmoviedbapp.di.koin
import org.m0skit0.android.dabestmoviedbapp.domain.toprated.TopTVShowsUseCase
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.PROFILE_NAME_TOP_SHOW_LIST
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.toTopRatedListingItem
import org.m0skit0.android.dabestmoviedbapp.state.TopRatedState
import org.m0skit0.android.dabestmoviedbapp.state.updateWithNextPage

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposable()
        }
//        lifecycleScope.launch {
//            topTVShowsUseCase(TopRatedState().updateWithNextPage()).fold({
//                it.printStackTrace()
//            }) {
//                withContext(Dispatchers.Main) {
//                    setContent {
//                        TopShowItem(topRatedTVShowData = it.topRatedShows.first())
//                    }
//                }
//            }
//        }
    }
}

@Composable
fun MainComposable() {
    NavHost(navController = rememberNavController(), startDestination = PROFILE_NAME_TOP_SHOW_LIST) {
        composable(PROFILE_NAME_TOP_SHOW_LIST) {
            TopShowList(topShowsListing().topRatedShows.map { it.toTopRatedListingItem() })
        }
    }
}

fun topShowsListing(topTVShowsUseCase: TopTVShowsUseCase = koin().get(NAMED_TOP_TV_SHOWS_USE_CASE)): TopRatedState =
    runBlocking(Dispatchers.IO) {
        topTVShowsUseCase(TopRatedState().updateWithNextPage()).fold({TopRatedState()}) { it }
    }
