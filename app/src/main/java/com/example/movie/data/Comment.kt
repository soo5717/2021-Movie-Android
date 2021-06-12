package com.example.movie.data

// 한줄평 데이터 클래스
data class Comment (
    val id: String,
    val time: String,
    val comment: String,
    val like: String,
    val rate: Float
)