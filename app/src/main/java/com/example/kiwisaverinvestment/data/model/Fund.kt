package com.example.kiwisaverinvestment.data.model

data class Fund(
    val name: String,
    val fundName: String,
    val fundDetails: List<String>,
    val fundInvestmentMix: List<FundInvestmentMix>,
)

data class FundInvestmentMix(
    val sectionTitle: String,
    val percentage: Int,
    val assetType: String,
)
