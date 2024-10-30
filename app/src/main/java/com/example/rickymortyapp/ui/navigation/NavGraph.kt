package com.example.rickymortyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rickymortyapp.ui.screens.CharacterDetailScreen
import com.example.rickymortyapp.ui.screens.CharacterListScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") { CharacterListScreen(navController) }
        composable("detail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt() ?: 1
            CharacterDetailScreen(navController, characterId)
        }
    }
}
