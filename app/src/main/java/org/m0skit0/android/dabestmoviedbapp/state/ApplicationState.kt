package org.m0skit0.android.dabestmoviedbapp.state

import java.io.Serializable

data class ApplicationState(
    val currentPage: Int = 1,
    val currentSeries: Long? = null,
    val genreMappingCache: Map<Int, String> = mapOf(),
) : Serializable
