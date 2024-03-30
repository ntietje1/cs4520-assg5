package com.cs4520.assignment5.data.http

import com.cs4520.assignment1.data.Product
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitInstance {
    private const val BASE_URL: String = "https://kgtttq6tg9.execute-api.us-east-2.amazonaws.com/"

    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}

interface Api {
    @GET("prod/random/")
    fun getProducts(@Query("page") page: Int = 0): Call<List<Product>>
}