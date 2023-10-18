package com.example.kiwisaverinvestment.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.databinding.ActivityHomeBinding
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