package com.cs4520.assignment5.application

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cs4520.assignment5.ui.login.LoginScreen
import com.cs4520.assignment5.ui.products.ProductScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "products") {
        composable("login") { LoginScreen(onLogin = { navController.navigate("products") }) }
        composable("products") { ProductScreen() }
    }
}