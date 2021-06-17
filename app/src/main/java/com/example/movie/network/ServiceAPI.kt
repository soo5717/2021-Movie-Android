package com.example.movie.network

import com.example.movie.data.MovieInfo
import com.example.movie.data.BoxOffice
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {
    // 일별 박스오피스
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    fun getBoxOffice(
        @Query("key") key: String,
        @Query("targetDt") targetDt: String
    ): Call<BoxOffice>

    // 영화 상세 정보
    @GET("movie/searchMovieInfo.json")
    fun getMovieInfo(
        @Query("key") key: String,
        @Query("movieCd") movieCd: String
    ): Call<MovieInfo>
}