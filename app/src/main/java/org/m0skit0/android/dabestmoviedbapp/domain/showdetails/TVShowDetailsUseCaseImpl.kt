package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import javax.inject.Inject

class TVShowDetailsUseCaseImpl @Inject constructor(
    private val tvShowDetailsRepository: TVShowDetailsRepository
): TVShowDetailsUseCase {

    override suspend fun tvShowDetails(id: Long): TVShowDetailsDomain =
        withContext(Dispatchers.IO) {
            tvShowDetailsRepository.tvShowDetails(id).toTVShowDetailsDomain()
        }
}
