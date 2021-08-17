package org.m0skit0.android.dabestmoviedbapp.data

interface TVShowDetailsRepository {
    suspend fun tvShowDetails(id: Long): TVShowDetailsData
}
