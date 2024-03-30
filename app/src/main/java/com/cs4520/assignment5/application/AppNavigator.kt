package com.cs4520.assignment5.application

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cs4520.assignment5.ui.login.LoginScreen
import com.cs4520.assignment5.ui.products.ProductScreen

private enum class Screen(val route: String) {
    Login("login"), Products("products")
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Products.route) {
        composable(Screen.Login.route) { LoginScreen(goToProducts = { navController.navigate(Screen.Products.route) }) }
        composable(Screen.Products.route) { ProductScreen() }
    }
}