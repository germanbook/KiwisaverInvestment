package com.example.kiwisaverinvestment.data.model

data class Question(
    val questionText: String,
    val answers: List<Answer>
)

data class Answer(
    val answerText: String,
    val score: Int
)
