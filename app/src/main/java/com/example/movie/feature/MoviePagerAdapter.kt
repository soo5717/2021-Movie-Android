package com.example.movie.feature

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movie.R

class MoviePagerAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val imageId: Int
        val title: String
        val info: String
        val fragment: MovieFragment?

        when(position) {
            0 -> {
                imageId = R.drawable.image1
                title = "${position + 1}. 군도"
                info = "관객 수 312,745 | 15세 이상 관람가"
                fragment = MovieFragment.newInstance(imageId, title, info)
            }
            1 -> {
                imageId = R.drawable.image2
                title = "${position + 1}. 공조"
                info = "관객 수 312,745 | 15세 이상 관람가"
                fragment = MovieFragment.newInstance(imageId, title, info)
            }
            2 -> {
                imageId = R.drawable.image3
                title = "${position + 1}. 더 킹"
                info = "관객 수 312,745 | 15세 이상 관람가"
                fragment = MovieFragment.newInstance(imageId, title, info)
            }
            else -> {
                fragment = MovieFragment.newInstance(0, "", "")
            }
        }
        return fragment
    }
}