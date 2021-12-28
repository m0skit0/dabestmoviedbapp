package org.m0skit0.android.dabestmoviedbapp.state

import java.io.Serializable

data class ApplicationState(
    val currentPage: Int = 1,
    val currentShowId: Long = -1L,
    val genreMappingCache: Map<Int, String> = mapOf(),
) : Serializable
