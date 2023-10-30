package com.example.kiwisaverinvestment.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.kiwisaverinvestment.R
import com.example.kiwisaverinvestment.common.Constants
import com.example.kiwisaverinvestment.databinding.ActivityHomeBinding
import com.example.kiwisaverinvestment.ui.mainscreen.MainScreenFragmentDirections
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), DrawerLocker {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarHome)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.content_screen) as NavHostFragment
        navController = navHostFragment.navController
        supportActionBar?.title = ""

        if (binding.navigationMenu is NavigationView) {
            val navigationView = binding.navigationMenu as NavigationView
            NavigationUI.setupWithNavController(navigationView, navController)
        }

        binding.homeButton.setOnClickListener {
            if (binding.root.isOpen) binding.root.close() else binding.root.open()
        }

        binding.navigationMenu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.tab_home -> {
                    navController.navigate(R.id.mainscreen)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_defensive -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.DEFENSIVE)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_conservative -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.CONSERVATIVE)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_balanced -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.BALANCED)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_balanced_growth -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.BALANCED_GROWTH)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_growth -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.GROWTH)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_aggressive_growth -> {
                    val action = MainScreenFragmentDirections.mainToInvestorType(Constants.AGGRESSIVE_GROWTH)
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_questionnaire -> {
                    val action = MainScreenFragmentDirections.mainToQuestionnaire()
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                R.id.tab_questionnaire_submit -> {
                    val action = MainScreenFragmentDirections.mainToSubmit()
                    navController.navigate(action)
                    binding.root.close()
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    //
                    return@setNavigationItemSelectedListener true
                }
            }

        }

    }

    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            binding.apply {
                this.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                this.homeButton.isVisible = false
            }
        } else {
            binding.apply {
                this.root.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                this.homeButton.isVisible = true
            }
        }
    }
}