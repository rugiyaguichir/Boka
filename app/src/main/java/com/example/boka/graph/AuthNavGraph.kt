package com.example.boka.graph

import com.example.boka.core.NormalScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.boka.ui.auth.SignInScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route= Graph.AUTHENTICATION,
        startDestination = NormalScreen.Login.route,
    ){
        composable(NormalScreen.Login.route){
            SignInScreen(navController)
        }

    }
}