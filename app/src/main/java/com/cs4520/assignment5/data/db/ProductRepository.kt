package com.cs4520.assignment5.data.db

import android.util.Log
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment5.data.http.Api
import com.cs4520.assignment5.data.http.RetrofitInstance
import retrofit2.awaitResponse

class ProductRepository(
    private val remoteDataSource: Api = RetrofitInstance.api,
    private val localDataSource: ProductDao,
) {

    suspend fun getProducts(page: Int = 0): List<Product> {
        var products: List<Product>
        try {
            products = getProductsNetwork(page)
            saveToDatabase(products)
        } catch (e: Exception) {
            Log.e("API", "Failed to get products from network: $e")
            products = getProductsLocal(page)
        }
        return products.ifEmpty { getProductsNetwork(page) }
    }

    suspend fun getProductsNetwork(page: Int): List<Product> {
        val products: MutableSet<Product> = mutableSetOf()
        val response = remoteDataSource.getProducts(page).awaitResponse()
        response.body()?.let { products.addAll(it) }
        return products.toList()
    }

    suspend fun getProductsLocal(page: Int): List<Product> {
        return localDataSource.get(page)
    }

    suspend fun saveToDatabase(products: List<Product>) {
        localDataSource.insertAll(products)
    }

}
