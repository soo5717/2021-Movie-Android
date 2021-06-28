package com.example.movie.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder2 {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    var service: ServiceAPI2

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // RxJava
            .build()

        service = retrofit.create(ServiceAPI2::class.java)
    }
}