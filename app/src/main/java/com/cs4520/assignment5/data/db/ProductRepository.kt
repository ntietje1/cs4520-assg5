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

   suspend fun getProducts(page: Int? = null): List<Product> {
        val products: MutableSet<Product> = mutableSetOf()
        try {
            Log.d("API","Fetching products from remote source")
            val response = remoteDataSource.getProducts(page).awaitResponse()
            if (response.isSuccessful) {
                Log.d("API", "Successfully retrieved products: ${response.body()}")
            } else {
                Log.e("API", "Failed to retrieve products: ${response.errorBody()}")
            }
            response.body()?.let { products.addAll(it) }
            localDataSource.insertAll(products.toList())
        } catch (e: Exception) {
            Log.d("Error: ", e.toString())
            Log.d("API","Failed to fetch products from remote source, trying database")
            products.addAll(localDataSource.get(page?:0))
        }
       if (products.isEmpty()) {
           products.addAll(localDataSource.get(page?:0))
       }
       Log.d("API","Returning products: $products")
        return products.toList()
    }
}
