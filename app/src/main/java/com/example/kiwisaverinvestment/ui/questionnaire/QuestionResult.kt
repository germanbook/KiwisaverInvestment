package com.example.kiwisaverinvestment.ui.questionnaire

import com.example.kiwisaverinvestment.data.model.Question

data class QuestionResult (
    val success: List<Question>? = null,
    val error: String? = null,
)