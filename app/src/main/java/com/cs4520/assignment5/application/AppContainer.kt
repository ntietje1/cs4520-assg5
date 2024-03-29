package com.cs4520.assignment5.application

import android.content.Context
import androidx.room.Room
import com.cs4520.assignment5.data.db.ProductDao
import com.cs4520.assignment5.data.db.ProductDatabase
import com.cs4520.assignment5.data.db.ProductRepository
import com.cs4520.assignment5.data.http.RetrofitInstance

class AppContainer {
    private val remoteDataSource = RetrofitInstance.api
    private lateinit var localDataSource: ProductDao
    lateinit var productRepository: ProductRepository

    fun initLocalDataSource(context: Context) {
        localDataSource =
            Room.databaseBuilder(
                context,
                ProductDatabase::class.java, "database-name"
            ).build().productDao()
    }

    fun initProductRepository(context: Context) {
        productRepository = ProductRepository(remoteDataSource, localDataSource, context)
    }
}