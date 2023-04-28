package com.fifty.socialnetwork.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fifty.socialnetwork.domain.model.Post
import com.fifty.socialnetwork.presentation.MainFeedScreen
import com.fifty.socialnetwork.presentation.activity.ActivityScreen
import com.fifty.socialnetwork.presentation.chat.ChatScreen
import com.fifty.socialnetwork.presentation.components.Post
import com.fifty.socialnetwork.presentation.createpost.CreatePostScreen
import com.fifty.socialnetwork.presentation.login.LoginScreen
import com.fifty.socialnetwork.presentation.postdetail.PostDetailScreen
import com.fifty.socialnetwork.presentation.profile.ProfileScreen
import com.fifty.socialnetwork.presentation.register.RegisterScreen
import com.fifty.socialnetwork.presentation.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }
        composable(Screen.PostDetailScreen.route) {
            PostDetailScreen(
                navController = navController,
                post = Post(
                    username = "Fazalul Abid",
                    imageUrl = "",
                    profilePictureUrl = "",
                    description = "Lorem ipsum dolor sit amet, consec tetur elit. " +
                            "Sedc do eiu consectetur smod tempor consectetur incididunt " +
                            "ut labore et dolore magna atur smod tempor consectetur tur" +
                            "ut labore et dolore magna atur smod tempor consectetur tur" +
                            "ut labore et dolore magna atur smod tempor consectetur tur" +
                            " smod tempor consecteturv tur smod tempor consecteturliqua.",
                    likeCount = 12,
                    commentCount = 7
                )
            )
        }
    }
}