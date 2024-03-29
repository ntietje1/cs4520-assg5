package com.cs4520.assignment5.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment5.application.App
import com.cs4520.assignment5.data.db.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading get() = _isLoading.asStateFlow()

    fun updateProducts(products: List<Product>) {
        _state.value = _state.value.copy(products = products)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[APPLICATION_KEY])
                return ProductViewModel((application as App).appContainer.productRepository) as T
            }
        }
    }

}