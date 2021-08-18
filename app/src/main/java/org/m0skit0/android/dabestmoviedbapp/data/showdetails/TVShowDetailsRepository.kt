package org.m0skit0.android.dabestmoviedbapp.data.showdetails

interface TVShowDetailsRepository {
    suspend fun tvShowDetails(id: Long): TVShowDetailsData
}
