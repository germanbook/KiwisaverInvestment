package com.example.kiwisaverinvestment.common

import android.content.Context
import java.io.IOException


const val JSON_FILE_NAME = "investor_types_and_funds.json"

object LoadJsonData {

    fun loadJSONFromAsset(context: Context): String? {
        val json: String?
        try {
            val inputStream = context.assets.open(JSON_FILE_NAME)
            val size = inputStream.available()
            val buffer = ByteArray(size)

            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }
}