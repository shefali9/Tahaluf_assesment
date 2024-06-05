package com.shefali.tahalufassessment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

fun interface UniversityApiService {
    @GET("search?country=United%20Arab%20Emirates")
    suspend fun getUniversities(): List<University>
}

object RetrofitClient {
    private const val BASE_URL = "http://universities.hipolabs.com/"

    val instance: UniversityApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UniversityApiService::class.java)
    }
}