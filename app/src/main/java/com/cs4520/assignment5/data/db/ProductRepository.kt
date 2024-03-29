package com.cs4520.assignment5.data.db

import android.content.Context
import androidx.room.Room
import com.cs4520.assignment5.data.http.Api
import com.cs4520.assignment5.data.http.RetrofitInstance

class ProductRepository(
    private val remoteDataSource: Api = RetrofitInstance.api,
    private val localDataSource: ProductDao,
    private val appContext: Context
) {


}