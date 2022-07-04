package com.devaruluis.exchanges

import Screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devaruluis.exchanges.ui.navigation.MainNavHost
import com.devaruluis.exchanges.ui.theme.ExchangesTheme
import com.devaruluis.loanscompose.ui.Menu
import com.devaruluis.loanscompose.ui.TopBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val allScreens = Screen.values().toList()
    val navController = rememberNavController()
    val backStackEntry = navController.currentBackStackEntryAsState()
    var currentScreen = Screen.fromRoute(backStackEntry.value?.destination?.route)
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val toggleDrawer = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Menu(
                allScreens = allScreens,
                onTabSelected = { screen ->
                    scope.launch {
                        navController.navigate(screen.name)
                        scaffoldState.drawerState.apply {
                            close()
                        }
                    }
                },
                currentScreen = currentScreen, closeMenu = {
                    toggleDrawer()
                }
            )
        },
        topBar = {
            TopBar(
                onMenuClick = { toggleDrawer() }
            )
        },
    ) { innerPadding ->
        MainNavHost(navController, modifier = Modifier.padding(innerPadding))
    }
    SnackbarHost(hostState = snackbarHostState)
}

