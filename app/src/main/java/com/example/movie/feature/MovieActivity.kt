package com.example.movie.feature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.config.MovieAPI
import com.example.movie.data.BoxOffice
import com.example.movie.data.MovieInfo
import com.example.movie.databinding.ActivityMovieBinding
import com.example.movie.network.RetrofitBuilder1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBoxOffice() // 일별 박스 오피스 요청
    }

    // 일별 박스오피스 요청
    private fun getBoxOffice() {
        // 어제 날짜 구하기 (API >= 26 사용 가능)
        val yesterday = LocalDate.now().minusDays(1)
        val targetDt = yesterday.format(DateTimeFormatter.BASIC_ISO_DATE)

        RetrofitBuilder1.service.getBoxOffice(MovieAPI.API_KEY, targetDt).enqueue(object: Callback<BoxOffice> {
            override fun onResponse(call: Call<BoxOffice>, response: Response<BoxOffice>) {
                if(response.isSuccessful) { // 200
                    val boxOfficeList = response.body()?.boxOfficeResult?.dailyBoxOfficeList
                    Log.d("test", "start===============")

                    if (boxOfficeList != null) {
                        for (movie in boxOfficeList)
                            RetrofitBuilder1.service.getMovieInfo(MovieAPI.API_KEY, movie.movieCd).enqueue(object: Callback<MovieInfo> {
                                override fun onResponse(
                                    call: Call<MovieInfo>,
                                    response: Response<MovieInfo>
                                ) {
                                    if(response.isSuccessful) {
                                        val movieInfo = response.body()?.movieInfoResult?.movieInfo
                                        Log.d("test", movieInfo?.movieNm.toString())
                                    }
                                }

                                override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                                    Log.e("영화 상세 정보 요청 에러", t.message.toString())
                                }

                            })
                    }

                    Log.d("test", "end===============")
                     //뷰페이저 어댑터 설정
                    binding.viewPager.adapter = MoviePagerAdapter(supportFragmentManager, lifecycle,
                        boxOfficeList as ArrayList<BoxOffice.BoxOfficeResult.DailyBoxOffice>
                    )
                }
            }

            override fun onFailure(call: Call<BoxOffice>, t: Throwable) {
                Log.e("일별 박스오피스 요청 에러", t.message.toString())
            }

        })
    }
}