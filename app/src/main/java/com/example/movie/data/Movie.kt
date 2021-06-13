package com.example.movie.data

data class Movie(
    val posterPath: String?, //포스터
    val movieNm: String?, // 영화명
    val audiAcc: String?, // 누적관객수
    val watchGradeNm: String? // 심의정보
)