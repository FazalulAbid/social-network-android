package com.fifty.socialnetwork.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fifty.socialnetwork.core.presentation.components.StandardScaffold
import com.fifty.socialnetwork.presentation.ui.theme.SocialNetworkTheme
import com.fifty.socialnetwork.core.presentation.components.Navigation
import com.fifty.socialnetwork.core.util.Screen
import dagger.hilt.android.AndroidEntryPoint

open class A

class B : A()

class Gen<T : A>

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialNetworkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()
                    StandardScaffold(
                        navController = navController,
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        state = scaffoldState,
                        modifier = Modifier.fillMaxSize(),
                        onFabClick = {
                            navController.navigate(Screen.CreatePostScreen.route)
                        }
                    ) {
                        Navigation(navController, scaffoldState)
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        val doesRouteMatch = backStackEntry?.destination?.route in listOf(
            Screen.MainFeedScreen.route,
            Screen.ChatScreen.route,
            Screen.ActivityScreen.route,
        )
        val isOwnProfile =
            backStackEntry?.destination?.route == "${Screen.ProfileScreen.route}?userId={userId}" &&
                    backStackEntry.arguments?.getString("userId") == null
        return doesRouteMatch || isOwnProfile
    }
}

class Solution {
    fun findTheDifference(s: String, t: String): Char {
        t.forEach { char ->
            if (!s.contains(char)) {
                return char
            }
        }
        return s.last()
    }
}