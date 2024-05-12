package com.example.newedutrax.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object{
        private var retrofit: Retrofit?=null
        @Synchronized fun getInstance():Retrofit{
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://edutrax.vercel.app/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()

            }
            return retrofit!!
        }
        fun getApis(): Webservices{
            return getInstance().create(Webservices::class.java)
        }

        private val gson: Gson by lazy {
            GsonBuilder()
                .setLenient()
                .create()
        }
    }




}