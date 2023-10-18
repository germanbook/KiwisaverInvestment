package com.example.kiwisaverinvestment.ui.description

import com.example.kiwisaverinvestment.data.model.request.Fund

data class InvestorTypeResult(
    val success: List<Fund>? = null,
    val error: String? = null,
)
