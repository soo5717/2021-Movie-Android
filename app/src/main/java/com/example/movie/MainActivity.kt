package com.example.movie

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButton()
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