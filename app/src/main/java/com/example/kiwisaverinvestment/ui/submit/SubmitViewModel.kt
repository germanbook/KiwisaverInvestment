package com.example.kiwisaverinvestment.ui.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kiwisaverinvestment.data.repo.submit.SubmitRepository
import kotlinx.coroutines.launch

class SubmitViewModel(
    private val repository: SubmitRepository,
): ViewModel() {

    private val _submitResult = MutableLiveData<Boolean>()
    val submitResult: LiveData<Boolean> = _submitResult

    fun submitToServer() {
        viewModelScope.launch {
            repository.submitToServer()
                .onSuccess { _submitResult.postValue(true) }
                .onFailure { _submitResult.postValue(false) }
        }
    }
}