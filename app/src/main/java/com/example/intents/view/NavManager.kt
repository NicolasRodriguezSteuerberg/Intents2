package com.example.intents.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Nav(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            MostrarInterfaz(navController = navController)
        }
        composable("camera"){
            Camera(navController = navController)
        }
    }
}