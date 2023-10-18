package com.example.kiwisaverinvestment.data.model.request

data class Fund(
    val name: String,
    val fundName: String,
    val fundDetails: List<String>,
    val fundInvestmentMix: List<FundInvestmentMix>,
)

data class FundInvestmentMix(
    val selectionTitle: String,
    val percentage: Int,
    val assetType: String,
)
