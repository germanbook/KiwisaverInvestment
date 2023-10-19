package com.example.kiwisaverinvestment.data.repo.questionnaire

import com.example.kiwisaverinvestment.data.model.Question

class QuestionnaireRepository(
    private val dataSource: QuestionnaireDataSource,
) {

    suspend fun listQuestions(): Result<List<Question>> {
        return try {
            dataSource.listQuestions().also {
                it.onSuccess { ArrayList(it) }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}