package com.summersoft.dictionary.features.dictionary.util

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.summersoft.dictionary.features.dictionary.presentation.WordInfoScreen

@ExperimentalAnimationApi
fun NavGraphBuilder.dictionaryGraph(navController: NavController){
    navigation(startDestination = DictionaryScreenRep.WordInfoScreen.route,
        route = DictionaryScreenRep.DictionaryInitScreen.route){
        composable(route = DictionaryScreenRep.WordInfoScreen.route){
            WordInfoScreen(navController = navController)
        }
    }
}