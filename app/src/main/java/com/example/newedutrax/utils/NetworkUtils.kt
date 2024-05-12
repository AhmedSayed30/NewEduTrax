package com.example.newedutrax.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object NetworkUtils {
    fun createJsonRequestBody(vararg params: Pair<String, String>): RequestBody =
        JSONObject(mapOf(*params)).toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
}