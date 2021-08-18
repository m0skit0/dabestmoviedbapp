package org.m0skit0.android.dabestmoviedbapp.data

import org.m0skit0.android.dabestmoviedbapp.BuildConfig

// TODO Unit test
fun String.toPreviewPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.PREVIEW_POSTER_SIZE}$this"

// TODO Unit test
fun String.toOriginalPosterFullUrl(): String =
    "${BuildConfig.IMAGE_BASE_URL}${BuildConfig.ORIGINAL_POSTER_SIZE}$this"
