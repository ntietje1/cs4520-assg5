package com.cs4520.assignment5.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cs4520.assignment1.data.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM product LIMIT 30")
    fun get(): List<Product>

    @Query("SELECT * FROM product LIMIT 30 OFFSET :page * 30")
    fun get(page: Int): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<Product>)

    @Query("DELETE FROM product")
    fun deleteAll()
}

