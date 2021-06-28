package com.example.movie.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder1 {
    private const val BASE_URL = "https://kobis.or.kr/kobisopenapi/webservice/rest/"
    var service: ServiceAPI1

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // RxJava
            .build()

        service = retrofit.create(ServiceAPI1::class.java)
    }
}