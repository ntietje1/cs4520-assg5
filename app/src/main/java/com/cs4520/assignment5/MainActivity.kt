package com.cs4520.assignment5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cs4520.assignment5.application.App
import com.cs4520.assignment5.application.AppNavigator
import com.cs4520.assignment5.ui.products.ProductScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as App).appContainer
        application?.applicationContext?.let { context -> appContainer.initProductRepository(context) }

        setContent {
            AppNavigator()
        }
    }
}