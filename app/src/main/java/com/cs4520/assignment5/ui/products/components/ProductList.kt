package com.cs4520.assignment5.ui.products.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cs4520.assignment1.data.Product

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Preview
@Composable
fun ProductListPreview() {
    ProductList(
        products = listOf(
            Product("Equipment 1", "Equipment", null, 5.99f),
            Product("Food 1", "Food", "Jan 1, 2023", 2.99f),
            Product("Equipment 2", "Equipment", null, 7.99f),
            Product("Food 2", "Food", "Jan 5, 2023", 3.99f),
        )
    )
}