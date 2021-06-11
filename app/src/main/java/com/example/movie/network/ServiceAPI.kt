package com.example.movie.network

import com.example.movie.data.MovieInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    // 상영 중인 영화 목록
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    fun getMovieInfo(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Call<MovieInfo>
}