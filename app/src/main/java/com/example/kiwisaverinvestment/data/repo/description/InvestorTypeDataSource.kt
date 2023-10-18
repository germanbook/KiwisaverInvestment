package com.example.kiwisaverinvestment.data.repo.description

import android.app.Application
import com.example.kiwisaverinvestment.data.model.request.Fund
import com.example.kiwisaverinvestment.common.LoadJsonData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InvestorTypeDataSource(
    private val application: Application,
) {
    suspend fun listInvestorType(): Result<List<Fund>> {
        return try {
            val jsonString = withContext(Dispatchers.IO) {
                LoadJsonData.loadJSONFromAsset(application.applicationContext)
            }
            val gson = Gson()
            val listType = object: TypeToken<List<Fund>>() {}.type
            val funds: List<Fund> = gson.fromJson(jsonString, listType)
            Result.success(funds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}