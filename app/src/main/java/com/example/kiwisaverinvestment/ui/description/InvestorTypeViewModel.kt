package com.example.kiwisaverinvestment.ui.description

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kiwisaverinvestment.data.repo.description.InvestorTypeRepository
import kotlinx.coroutines.launch

class InvestorTypeViewModel(
    private val repository: InvestorTypeRepository,
): ViewModel() {

    private val _investorTypeResult = MutableLiveData<InvestorTypeResult>()
    val investorTypeResult: LiveData<InvestorTypeResult> = _investorTypeResult

    private fun getInvestorType() {
        viewModelScope.launch {
            repository.listInvestorType()
                .onSuccess { _investorTypeResult.postValue(InvestorTypeResult(success = it)) }
                .onFailure { _investorTypeResult.postValue(InvestorTypeResult(error = it.message?: "Failed")) }
        }
    }

    fun dataPreload() {
        getInvestorType()
    }
}