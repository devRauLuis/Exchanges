package com.devaruluis.exchanges.ui.navigation

import Screen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.devaruluis.exchanges.ui.view.CoinRegistryBody
import com.devaruluis.exchanges.ui.view.CoinsScreenBody

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.name,
        modifier = modifier
    ) {
        composable(Screen.Home.name) {
            CoinsScreenBody()
        }

        composable(
            Screen.Registry.name
        ) {
            CoinRegistryBody()
        }

        val coinRegistryName = Screen.Registry.name
        composable(
            route = "$coinRegistryName/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "app://$coinRegistryName/{id}"
                }
            ),

            ) {
            val id = it.arguments?.getString("id")
            println("id arg: $id")
            CoinRegistryBody(id = id)
        }
    }
}
