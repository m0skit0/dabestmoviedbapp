package org.m0skit0.android.dabestmoviedbapp.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Progress()
        }
//        lifecycleScope.launch {
//            topTVShowsUseCase(TopRatedState().updateWithNextPage()).fold({
//                it.printStackTrace()
//            }) {
//                withContext(Dispatchers.Main) {
//                    setContent {
//                        TopShowItem(topRatedTVShowData = it.topRatedShows.first())
//                    }
//                }
//            }
//        }
    }
}
