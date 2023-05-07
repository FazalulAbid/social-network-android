package com.fifty.socialnetwork.core.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.presentation.MainFeedScreen
import com.fifty.socialnetwork.featureactivity.presentation.activity.ActivityScreen
import com.fifty.socialnetwork.featurechat.presentation.chat.ChatScreen
import com.fifty.socialnetwork.featurepost.presentation.createpost.CreatePostScreen
import com.fifty.socialnetwork.featureprofile.presentation.editprofile.EditProfileScreen
import com.fifty.socialnetwork.featureauth.presentation.login.LoginScreen
import com.fifty.socialnetwork.presentation.personlist.PersonListScreen
import com.fifty.socialnetwork.featurepost.presentation.postdetail.PostDetailScreen
import com.fifty.socialnetwork.featureprofile.presentation.profile.ProfileScreen
import com.fifty.socialnetwork.featureauth.presentation.register.RegisterScreen
import com.fifty.socialnetwork.featureprofile.presentation.search.SearchScreen
import com.fifty.socialnetwork.featureauth.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(
                navController = navController,
                scaffoldState = scaffoldState
            )
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
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }
        composable(Screen.PersonListScreen.route) {
            PersonListScreen(navController = navController)
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