package com.example.movie.feature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.R
import com.example.movie.data.Movie

class MoviePagerAdapter(fm: FragmentManager, lc: Lifecycle,
                        private var data: ArrayList<Movie>) : FragmentStateAdapter(fm, lc) {

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment {
        val movie = data[position]
        val imageId = R.drawable.image1
        val title = "${position + 1}. ${movie.movieNm}"
        val info = "관객 수 ${movie.audiAcc} | ${movie.watchGradeNm}"

        return MovieFragment.newInstance(imageId,title, info)
    }
}