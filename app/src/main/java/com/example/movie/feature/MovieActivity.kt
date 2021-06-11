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

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val call = RetrofitBuilder.service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        call.getMovieInfo(MovieAPI.API_KEY, "20210610").enqueue(object: Callback<MovieInfo> {
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                if(response.isSuccessful) { // 200
                    val result = response.body()
                    Log.d("결과 테스트",
                        result?.boxOfficeResult?.dailyBoxOfficeList?.get(0)?.movieNm.toString()
                    )
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