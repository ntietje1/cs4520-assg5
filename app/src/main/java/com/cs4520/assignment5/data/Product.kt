package com.cs4520.assignment1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey val name: String,
    @ColumnInfo val type: String?,
    @ColumnInfo val expiryDate: String?,
    @ColumnInfo val price: Float?
)

enum class ProductType {
    Equipment,
    Food,
    Other
}
