package com.example.kiwisaverinvestment.ui.questionnaire.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kiwisaverinvestment.KiwisaverInvestmentApplication
import com.example.kiwisaverinvestment.data.model.User

class QuestionnaireResultViewModel: ViewModel() {

    fun getInvestorTypeByScore(score: Int): User {

        val user = KiwisaverInvestmentApplication.user

        when (score) {
            in 5..12 -> {
                user?.apply {
                    this.investorType = 0
                    this.investorTypeName = "Defensive"
                }
            }
            in 13..20 -> {
                user?.apply {
                    this.investorType = 1
                    this.investorTypeName = "Conservative"
                }
            }

            in 21..29 -> {
                user?.apply {
                    this.investorType = 2
                    this.investorTypeName = "Balanced"
                }
            }
            in 30..37 -> {
                user?.apply {
                    this.investorType = 3
                    this.investorTypeName = "Balanced Growth"
                }
            }
            in 38..44 -> {
                user?.apply {
                    this.investorType = 4
                    this.investorTypeName = "Growth"
                }
            }
            in 45..50 -> {
                user?.apply {
                    this.investorType = 5
                    this.investorTypeName = "Aggressive Growth"
                }
            }
            else -> "Unknown"
        }
        return user
    }

}