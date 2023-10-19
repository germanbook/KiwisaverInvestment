package com.example.kiwisaverinvestment.data.repo.description

import com.example.kiwisaverinvestment.data.model.Fund

class InvestorTypeRepository(
    private val dataSource: InvestorTypeDataSource,
) {

    private var investorTypes: MutableList<Fund> = ArrayList()

    suspend fun listInvestorType(): Result<List<Fund>> {
        return try {
            dataSource.listInvestorType().also {
                it.onSuccess { investorTypes = ArrayList(it) }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}