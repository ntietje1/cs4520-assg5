package com.cs4520.assignment5.ui.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cs4520.assignment5.ui.products.components.ProductList

@Composable
fun ProductScreen(viewModel: ProductViewModel = viewModel(factory = ProductViewModel.Factory)) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            text = "Products",
            fontWeight = FontWeight.Bold
            )
        ProductList(products = state.products)
    }
}