package org.m0skit0.android.dabestmoviedbapp.data

import org.m0skit0.android.dabestmoviedbapp.BuildConfig

fun String.toPreviewPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.PREVIEW_POSTER_SIZE}$this"

fun String.toOriginalPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.ORIGINAL_POSTER_SIZE}$this"
