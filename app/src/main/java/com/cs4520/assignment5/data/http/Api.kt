package com.cs4520.assignment5.data.http

import com.cs4520.assignment1.data.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("prod")
    fun getProducts(@Query("page") page: Int? = null): Call<List<Product>>
}