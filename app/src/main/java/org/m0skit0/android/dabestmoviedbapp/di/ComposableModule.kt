package org.m0skit0.android.dabestmoviedbapp.di

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.similarshows.SimilarTVShowsPager
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList

var NAMED_TOP_SHOW_LIST_COMPOSABLE = named("NAMED_TOP_SHOW_LIST_COMPOSABLE")
var NAMED_SIMILAR_TV_SHOWS_COMPOSABLE = named("NAMED_SIMILAR_TV_SHOWS_COMPOSABLE")

@ExperimentalPagerApi
@ExperimentalCoilApi
val composableModule = module {
    single<@Composable () -> Unit>(NAMED_TOP_SHOW_LIST_COMPOSABLE) { (navigateToDetails: (id: Long) -> Unit) ->
        @Composable { TopShowList(navigateToDetails, getViewModel()) }
    }
    single<@Composable () -> Unit>(NAMED_SIMILAR_TV_SHOWS_COMPOSABLE) { (tvShowId: Long)  ->
        @Composable { SimilarTVShowsPager(getSimilarTVShowsViewModel(tvShowId)) }
    }
}

fun getTopShowListComposable(navigateToDetails: (id: Long) -> Unit): @Composable () -> Unit =
    koin().get(NAMED_TOP_SHOW_LIST_COMPOSABLE) { parametersOf(navigateToDetails) }

fun getSimilarTVShowsComposable(tvShowId: Long): @Composable () -> Unit =
    koin().get(NAMED_SIMILAR_TV_SHOWS_COMPOSABLE) { parametersOf(tvShowId) }
