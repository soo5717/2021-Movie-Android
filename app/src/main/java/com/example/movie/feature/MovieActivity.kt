package com.example.movie.feature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.config.MovieAPI
import com.example.movie.data.MovieInfo
import com.example.movie.data.BoxOffice
import com.example.movie.data.Movie
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

    private var movie: ArrayList<Movie> = ArrayList()

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

        call.getBoxOffice(MovieAPI.API_KEY, targetDt).enqueue(object: Callback<BoxOffice> {
            override fun onResponse(call: Call<BoxOffice>, response: Response<BoxOffice>) {
                if(response.isSuccessful) { // 200
                    val boxOfficeList = response.body()?.boxOfficeResult?.dailyBoxOfficeList

                    if (boxOfficeList != null) {
                        for(boxOffice in boxOfficeList)
                            // 영화 상세 정보 요청
                            getMovieInfo(boxOffice.movieCd, boxOffice.movieNm, boxOffice.audiAcc)
                    }
                    Log.d("영화 목록2", "movie[0].movieNm.toString()")
                    // 뷰페이저 어댑터 설정
                    binding.viewPager.adapter = MoviePagerAdapter(supportFragmentManager, lifecycle, movie)
                }
            }

            override fun onFailure(call: Call<BoxOffice>, t: Throwable) {
                Log.e("일별 박스오피스 요청 에러", t.message.toString())
            }

        })
    }

    // 영화 상세 정보 요청
    private fun getMovieInfo(movieCd: String, movieNm: String, audiAcc: String) {
        call.getMovieInfo(MovieAPI.API_KEY, movieCd).enqueue(object: Callback<MovieInfo> {
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                if(response.isSuccessful) { // 200
                    val movieInfo = response.body()?.movieInfoResult?.movieInfo
                    val watchGradeNm = movieInfo?.audits?.get(0)?.watchGradeNm?: ""
                    movie.add(Movie("", movieNm, audiAcc, watchGradeNm))
                    Log.d("영화 목록1", movieNm)
                }
            }

            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                Log.e("영화 상세 정보 요청 에러", t.message.toString())
            }

        })
    }
}