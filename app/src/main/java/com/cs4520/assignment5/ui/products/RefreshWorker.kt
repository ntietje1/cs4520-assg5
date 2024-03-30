package com.cs4520.assignment5.ui.products

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cs4520.assignment5.application.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RefreshWorker(context: Context, params: WorkerParameters): CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                val app = applicationContext as App
                val products = app.appContainer.productRepository.getProducts()
                app.appContainer.productRepository.saveToDatabase(products)
                Log.e(WORK_NAME, "REFRESHED LOCAL DB")
                Result.success()
            } catch (e: Exception) {
                Result.retry()
            }
        }
    }

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
}