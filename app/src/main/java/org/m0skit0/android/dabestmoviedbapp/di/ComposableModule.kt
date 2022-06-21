package org.m0skit0.android.dabestmoviedbapp.di

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.dabestmoviedbapp.presentation.showdetails.TVShowDetailItem
import org.m0skit0.android.dabestmoviedbapp.presentation.similarshows.SimilarTVShowsPager
import org.m0skit0.android.dabestmoviedbapp.presentation.topratedlist.TopShowList

var NAMED_TOP_SHOW_LIST_COMPOSABLE = named("NAMED_TOP_SHOW_LIST_COMPOSABLE")
var NAMED_SIMILAR_TV_SHOWS_COMPOSABLE = named("NAMED_SIMILAR_TV_SHOWS_COMPOSABLE")
var NAMED_TV_SHOW_DETAILS_COMPOSABLE = named("NAMED_TV_SHOW_DETAILS_COMPOSABLE")

@ExperimentalPagerApi
@ExperimentalCoilApi
val composableModule = module {
    factory<@Composable () -> Unit>(NAMED_TOP_SHOW_LIST_COMPOSABLE) { (navigateToDetails: (id: Long) -> Unit) ->
        @Composable { TopShowList(navigateToDetails, getViewModel()) }
    }
    factory<@Composable () -> Unit>(NAMED_SIMILAR_TV_SHOWS_COMPOSABLE) { (tvShowId: Long)  ->
        @Composable { SimilarTVShowsPager(getSimilarTVShowsViewModel(tvShowId)) }
    }
    factory<@Composable () -> Unit>(NAMED_TV_SHOW_DETAILS_COMPOSABLE) { (tvShowId: Long)  ->
        @Composable { TVShowDetailItem(tvShowId, getViewModel()) }
    }
}

fun getTopShowListComposable(navigateToDetails: (id: Long) -> Unit): @Composable () -> Unit =
    koin().get(NAMED_TOP_SHOW_LIST_COMPOSABLE) { parametersOf(navigateToDetails) }

fun getSimilarTVShowsComposable(tvShowId: Long): @Composable () -> Unit =
    koin().get(NAMED_SIMILAR_TV_SHOWS_COMPOSABLE) { parametersOf(tvShowId) }

fun getTVShowDetailItemComposable(tvShowId: Long): @Composable () -> Unit =
    koin().get(NAMED_TV_SHOW_DETAILS_COMPOSABLE) { parametersOf(tvShowId) }
