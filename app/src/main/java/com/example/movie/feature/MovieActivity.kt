package com.example.movie.feature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.config.MovieAPI
import com.example.movie.data.MovieInfo
import com.example.movie.databinding.ActivityMovieBinding
import com.example.movie.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val call = RetrofitBuilder.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 어제 날짜 구하기 (API >= 26 사용 가능)
        val currentDateTime = LocalDate.now().minusDays(1)
        val today = currentDateTime.format(DateTimeFormatter.BASIC_ISO_DATE)

        call.getMovieInfo(MovieAPI.API_KEY, today).enqueue(object: Callback<MovieInfo> {
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                if(response.isSuccessful) { // 200
                    val result = response.body()
                }
            }

            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                Log.e("영화 목록 요청 에러", t.message.toString())
            }

        })

        // 뷰페이저 어댑터 설정
        binding.viewPager.adapter = MoviePagerAdapter(supportFragmentManager, lifecycle)
    }
}