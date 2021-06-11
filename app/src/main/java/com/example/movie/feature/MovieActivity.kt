package com.example.movie.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뷰페이저 어댑터 설정
        binding.viewPager.adapter = MoviePagerAdapter(supportFragmentManager, lifecycle)
    }
}