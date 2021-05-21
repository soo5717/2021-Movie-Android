package com.example.movie

import android.os.Bundle
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
        // 버튼 클릭 시 색상 바뀌고 숫자 올라가기
        binding.buttonThumbUp.setOnClickListener {
            if (it.isSelected) { // 취소했을 때
                binding.textViewThumbUp.text = (binding.textViewThumbUp.text.toString().toInt() - 1).toString()
            } else { // 선택했을 때
                if(binding.buttonThumbDown.isSelected) { // 선택된 상태면
                    binding.textViewThumbDown.text = (binding.textViewThumbDown.text.toString().toInt() - 1).toString()
                    binding.buttonThumbDown.isSelected = !binding.buttonThumbDown.isSelected
                }
                binding.textViewThumbUp.text = (binding.textViewThumbUp.text.toString().toInt() + 1).toString()
            }
            it.isSelected = !it.isSelected
        }
        binding.buttonThumbDown.setOnClickListener {
            if (it.isSelected) { // 취소했을 때
                binding.textViewThumbDown.text = (binding.textViewThumbDown.text.toString().toInt() - 1).toString()
            } else { // 선택 했을 때
                if(binding.buttonThumbUp.isSelected) { // 선택된 상태면
                    binding.textViewThumbUp.text = (binding.textViewThumbUp.text.toString().toInt() - 1).toString()
                    binding.buttonThumbUp.isSelected = !binding.buttonThumbUp.isSelected
                }
                binding.textViewThumbDown.text = (binding.textViewThumbDown.text.toString().toInt() + 1).toString()
            }
            it.isSelected = !it.isSelected
        }
    }
}