package com.cs4520.assignment5.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.cs4520.assignment1.data.Product
import com.cs4520.assignment5.application.App
import com.cs4520.assignment5.data.db.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProductViewModel(private val repository: ProductRepository, private val workManager: WorkManager) : ViewModel() {
    private val _state = MutableStateFlow(ProductState())
    val state = _state.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        scheduleProductRefresh()
        viewModelScope.launch {
            updateIsLoading(true)
            updateProducts(repository.getProducts())
            updateIsLoading(false)
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun updateProducts(products: List<Product>) {
        _state.value = _state.value.copy(products = products)
    }

    private fun scheduleProductRefresh() {
        val workRequest = PeriodicWorkRequestBuilder<RefreshWorker>(1, TimeUnit.HOURS)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build(),
            ).build()
        workManager.enqueueUniquePeriodicWork(
            RefreshWorker.WORK_NAME, ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

                val application = checkNotNull(extras[APPLICATION_KEY])
                return ProductViewModel(
                    (application as App).appContainer.productRepository, WorkManager.getInstance(application)
                ) as T
            }
        }
    }


}