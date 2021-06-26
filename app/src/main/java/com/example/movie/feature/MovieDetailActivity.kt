package com.example.movie.feature

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.config.MovieAPI
import com.example.movie.data.Comment
import com.example.movie.data.MovieInfo
import com.example.movie.databinding.ActivityMovieDetailBinding
import com.example.movie.network.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val call = RetrofitBuilder.service

    private var movieCd: String? = null
    private var rank: String? = null
    private var totalWathc: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movieCd = intent.getStringExtra("movieCd")
        rank = intent.getStringExtra("rank")
        totalWathc = intent.getStringExtra("totalWatch")
        Log.d("movieCd", movieCd.toString())

        initView()
        initButton()
    }

    // 뷰 초기화 메서드
    private fun initView() {
        if (movieCd != null)
            getMovieInfo(movieCd.toString())
        // 한줄평 리사이클러뷰
        val adapter = CommentAdapter()

        // 테스트 아이템
        adapter.items.add(Comment("abc123", "10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", "추천 0", 3.0f))
        adapter.items.add(Comment("def123", "10분전", "적당히 재밌다. 오랜만에 잠 안오는 영화 봤네요.", "추천 0", 4.5f))

        binding.recyclerView.adapter = adapter
    }

     // 영화 상세 정보 요청
    private fun getMovieInfo(movieCd: String) {
        call.getMovieInfo(MovieAPI.API_KEY, movieCd).enqueue(object: Callback<MovieInfo> {
            override fun onResponse(call: Call<MovieInfo>, response: Response<MovieInfo>) {
                if(response.isSuccessful) { // 200
                    val result = response.body()?.movieInfoResult?.movieInfo

                    // 뷰 추가 필요함!!!
                    if (result != null) {
                        val openDate = "${result.openDt} 개봉"
                        val showTime = "${result.genres[0].genreNm} / ${result.showTm} 분"
                        binding.textViewTitle.text = result.movieNm
                        binding.textViewOpenDate.text = openDate
                        binding.textViewShowTime.text = showTime
                        binding.textViewRank.text = rank
                        binding.textViewTotalWatch.text = totalWathc

                        //TODO 이미지 넣기 필요

                    }
                }
            }

            override fun onFailure(call: Call<MovieInfo>, t: Throwable) {
                Log.e("영화 상세 정보 요청 에러", t.message.toString())
            }

        })
    }

    // 버튼 동작 초기화 메서드
    private fun initButton() {
        // 좋아요, 싫어요 버튼
        binding.buttonThumbUp.setOnClickListener {
            thumbSelected(binding.buttonThumbUp, binding.textViewThumbUp, binding.buttonThumbDown, binding.textViewThumbDown, it.isSelected)
        }
        binding.buttonThumbDown.setOnClickListener {
            thumbSelected(binding.buttonThumbDown, binding.textViewThumbDown, binding.buttonThumbUp, binding.textViewThumbUp, it.isSelected)
        }
    }

    // 좋아요, 싫어요 동작 구현 메서드
    private fun thumbSelected(button1: ImageButton, textView1: TextView, button2: ImageButton, textView2: TextView, isSelected: Boolean) {
        val count1 = textView1.text.toString().toInt()
        if(isSelected) { // 선택 취소
            textView1.text = (count1 - 1).toString()
        } else { // 선택
            if (button2.isSelected) { // 다른 버튼이 선택되어 있을 경우
                val count2 = textView2.text.toString().toInt()
                textView2.text = (count2 - 1).toString()
                button2.isSelected = !button2.isSelected
            }
            textView1.text = (count1 + 1).toString()
        }
        button1.isSelected = !button1.isSelected
    }
}