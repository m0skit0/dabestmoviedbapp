package org.m0skit0.android.dabestmoviedbapp.domain.showdetails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.dabestmoviedbapp.BuildConfig
import org.m0skit0.android.dabestmoviedbapp.data.showdetails.TVShowDetailsRepository
import javax.inject.Inject
import javax.inject.Named

// TODO Unit test
class TVShowDetailsUseCaseImpl @Inject constructor(
    @Named(BuildConfig.NAMED_SHOW_DETAILS_REPOSITORY)
    private val tvShowDetailsRepository: TVShowDetailsRepository
): TVShowDetailsUseCase {

    override suspend fun tvShowDetails(id: Long): TVShowDetailsDomain =
        withContext(Dispatchers.IO) {
            tvShowDetailsRepository.tvShowDetails(id).toTVShowDetailsDomain()
        }
}
