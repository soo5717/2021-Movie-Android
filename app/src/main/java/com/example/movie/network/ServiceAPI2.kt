package com.example.movie.network

import com.example.movie.data.SearchMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI2 {
    // 영화 상세 정보
    @GET("search/movie")
    fun getSearchMovie(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<SearchMovie>
}