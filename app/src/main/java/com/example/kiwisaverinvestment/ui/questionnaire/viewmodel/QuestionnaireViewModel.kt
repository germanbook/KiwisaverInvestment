package com.example.kiwisaverinvestment.ui.questionnaire.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kiwisaverinvestment.data.model.Question
import com.example.kiwisaverinvestment.data.repo.questionnaire.QuestionnaireRepository
import com.example.kiwisaverinvestment.ui.questionnaire.QuestionResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionnaireViewModel(
    private val repository: QuestionnaireRepository,
): ViewModel() {

    private val _questionResult = MutableLiveData<QuestionResult>()

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> = _question

    private var questionIndex = 0;


    private suspend fun getQuestions() {
        withContext(Dispatchers.IO) {
            repository.listQuestions()
                .onSuccess { _questionResult.postValue(QuestionResult(success = it)) }
                .onFailure { _questionResult.postValue(QuestionResult(error = it.message?: "Failed")) }
        }
    }

    fun dataPreload() {
        viewModelScope.launch {
            getQuestions()
            getNextQuestion()
        }

    }

    // True: Finished
    fun isQuestionnaireFinished(): Boolean {
        return questionIndex == _questionResult?.value?.success?.size
    }

    suspend fun getNextQuestion() {
        withContext(Dispatchers.IO) {
            _questionResult.value?.success?.apply {

                if (questionIndex < this.size) {
                    _question.postValue(this[questionIndex])
                    questionIndex ++
                }
            }
        }
    }
}