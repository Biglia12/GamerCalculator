package com.app.gamercalculator.utils

import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.InputStreamReader

class JsonFileReader(val context: Context) {
    inline fun <reified T> readJsonFromAssets(fileName: String): T {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        return if (T::class.java.isAssignableFrom(List::class.java)) {
            val type = object : TypeToken<T>() {}.type
            Gson().fromJson<T>(reader, type)
        } else {
            Gson().fromJson(reader, T::class.java)
        }
    }
}