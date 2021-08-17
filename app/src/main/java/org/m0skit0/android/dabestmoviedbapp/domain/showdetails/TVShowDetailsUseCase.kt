package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

interface TVShowDetailsUseCase {
    suspend fun tvShowDetails(id: Long): TVShowDetailsDomain
}
