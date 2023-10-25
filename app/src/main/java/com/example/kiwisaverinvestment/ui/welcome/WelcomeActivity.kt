package com.example.kiwisaverinvestment.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kiwisaverinvestment.databinding.ActivityWelcomeBinding
import com.example.kiwisaverinvestment.ui.home.HomeActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnWelcome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}