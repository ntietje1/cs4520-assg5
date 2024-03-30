package com.cs4520.assignment5.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs4520.assignment1.data.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product LIMIT 30")
    suspend fun get(): List<Product>

    @Query("SELECT * FROM product LIMIT 30 OFFSET :page * 30")
    suspend fun get(page: Int = 0): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM product")
    suspend fun deleteAll()
}

