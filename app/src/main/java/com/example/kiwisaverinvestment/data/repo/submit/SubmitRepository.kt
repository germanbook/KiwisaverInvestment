package com.example.kiwisaverinvestment.data.repo.submit

class SubmitRepository(
    private val dataSource: SubmitDataSource,
) {
    suspend fun submitToServer(): Result<Unit> {
        return try {
            dataSource.submitToServer().also {
                it.onSuccess { Result.success(Unit) }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}