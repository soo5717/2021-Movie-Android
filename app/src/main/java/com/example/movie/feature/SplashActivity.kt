package com.example.movie.feature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 파라미터 없는 Handler 는 Deprecated 됨.
        // 다양한 방식 있으니까 찾아봐도 좋을 듯 함.
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MovieActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}