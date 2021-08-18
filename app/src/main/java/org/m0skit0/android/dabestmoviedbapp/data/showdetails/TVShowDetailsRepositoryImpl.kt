package org.m0skit0.android.dabestmoviedbapp.data.showdetails

import org.m0skit0.android.dabestmoviedbapp.data.retrofit.TVShowDetailsService
import javax.inject.Inject

class TVShowDetailsRepositoryImpl @Inject constructor(
    private val tvShowDetailsService: TVShowDetailsService
) : TVShowDetailsRepository {

    override suspend fun tvShowDetails(id: Long): TVShowDetailsData =
        tvShowDetailsService.tvShowDetails(id = id).toTVShowDetailsData()
}
