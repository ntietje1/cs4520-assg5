package com.cs4520.assignment5.ui.products

import com.cs4520.assignment1.data.Product

data class ProductState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = true
)