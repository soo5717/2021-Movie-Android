package com.example.movie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MoviePagerAdapter : FragmentStateAdapter {
    constructor(fm: FragmentManager, lc: Lifecycle) : super(fm, lc)

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        var imageId: Int = 0
        var title: String = ""
        var info: String = ""
        var fragment: MovieFragment? = null

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