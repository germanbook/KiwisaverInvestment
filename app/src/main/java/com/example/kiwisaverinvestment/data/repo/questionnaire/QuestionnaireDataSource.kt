package com.example.kiwisaverinvestment.data.repo.questionnaire

import com.example.kiwisaverinvestment.data.model.Answer
import com.example.kiwisaverinvestment.data.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class QuestionnaireDataSource {

    // Normally data fetched from API, made it suspend function
    suspend fun listQuestions(): Result<List<Question>> {
        return try {
            delay(1000)
            val questions = withContext(Dispatchers.IO) {
                listOf(
                    Question(
                        "When do you plan to make a significant lump sum withdrawal from your portfolio?",
                        listOf(
                            Answer("Within 2 years – you need a DEFENSIVE TYPE fund", 1),
                            Answer("In 2 to 5 years – you need a CONSERVATIVE TYPE fund", 3),
                            Answer("In 6 to 10 years – continue to next question", 5),
                            Answer("In 11 to 20 years – continue to next question", 7),
                            Answer("More than 20 years – continue to next question", 10)
                        )
                    ),
                    Question(
                        "Which of the following questions best describes your thoughts about risks and returns?",
                        listOf(
                            Answer("I want to minimize my risk and am willing to accept low returns", 1),
                            Answer("I am willing to take a moderate amount of risk to generate low to medium returns", 3),
                            Answer("I am willing to take a reasonable amount of risk to provide a more medium return", 5),
                            Answer("In order to receive higher returns, I am willing to take a relatively high amount of risk", 7),
                            Answer("I want to maximize my returns and am willing to accept a high level of risk", 10)
                        )
                    ),
                    Question(
                        "Protecting my investment portfolio from a fall in value at any time is more important to me than achieving high returns?",
                        listOf(
                            Answer("Strongly Agree", 1),
                            Answer("Agree", 3),
                            Answer("Neither agree or disagree", 5),
                            Answer("Disagree", 7),
                            Answer("Strongly Disagree", 10)
                        )
                    ),
                    Question(
                        "Consider you have an investment balance of at least $20,000 or more. If after a short period of time (less than 1 year) your balance has dropped in value, which statement reflects best how you would feel about this?",
                        listOf(
                            Answer("I would be unhappy with any drop in value", 1),
                            Answer("I would be OK with a drop in value of no more than 5%", 3),
                            Answer("I would be OK with a drop in value of no more than 10%", 5),
                            Answer("I would be OK with a drop in value of up to 15%", 7),
                            Answer("I would be OK with a drop in value of up to 20%", 10)
                        )
                    ),
                    Question(
                        "How often would you tend to worry about your investment returns?",
                        listOf(
                            Answer("Daily", 1),
                            Answer("Monthly", 3),
                            Answer("Quarterly", 5),
                            Answer("Annually", 7),
                            Answer("Never or only occasionally over the longer term", 10)
                        )
                    )
                )
            }
            Result.success(questions)
        }
        catch (e: Exception) {
            Result.failure(e)
        }
    }
}