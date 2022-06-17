package org.m0skit0.android.dabestmoviedbapp.presentation

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue

@ExperimentalPagerApi
fun Modifier.animatePageTransition(pagerScope: PagerScope, page: Int) = graphicsLayer {
    val pageOffset = pagerScope.calculateCurrentOffsetForPage(page).absoluteValue

    lerp(
        start = 0.60f,
        end = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    ).also { scale ->
        scaleX = scale
        scaleY = scale
    }

    alpha = lerp(
        start = 0.1f,
        end = 1f,
        fraction = 1f - pageOffset.coerceIn(0f, 1f)
    )
}

private fun lerp(start: Float, end: Float, fraction: Float): Float = start + fraction * (end - start)
