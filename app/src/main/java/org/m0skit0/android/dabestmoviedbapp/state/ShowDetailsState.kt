package org.m0skit0.android.dabestmoviedbapp.state

import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsData
import java.io.Serializable

data class ShowDetailsState(
    val currentShowId: Long = -1L,
    val tvShowDetails: TVShowDetailsData? = null,
) : Serializable
