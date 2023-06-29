package com.example.boka.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.boka.core.NormalScreen
import com.example.boka.ui.detail.BookDetailScreen

fun NavGraphBuilder.detailNavGraph(navController: NavHostController){
    navigation(
        route= Graph.DETAIL,
        startDestination = "${NormalScreen.BookDetail.route}/{bookId}",
    ){
        composable(
            "${NormalScreen.BookDetail.route}/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ){
            val bookId = it.arguments?.getInt("bookId")
            BookDetailScreen(navController, bookId)
        }
    }
}