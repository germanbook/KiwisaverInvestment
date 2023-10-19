package com.example.kiwisaverinvestment.data.repo.submit

import com.example.kiwisaverinvestment.KiwisaverInvestmentApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class SubmitDataSource {

    suspend fun submitToServer(): Result<Unit> {
        return try {
            // send user data to server
            withContext(Dispatchers.IO) {

                KiwisaverInvestmentApplication.user
                delay(2000)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}