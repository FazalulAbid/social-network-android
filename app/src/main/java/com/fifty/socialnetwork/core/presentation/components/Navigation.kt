package com.fifty.socialnetwork.core.presentation.components

import android.content.Intent
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import coil.ImageLoader
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.featurepost.presentation.mainfeed.MainFeedScreen
import com.fifty.socialnetwork.featureactivity.presentation.ActivityScreen
import com.fifty.socialnetwork.featurechat.presentation.chat.ChatScreen
import com.fifty.socialnetwork.featurepost.presentation.createpost.CreatePostScreen
import com.fifty.socialnetwork.featureprofile.presentation.editprofile.EditProfileScreen
import com.fifty.socialnetwork.featureauth.presentation.login.LoginScreen
import com.fifty.socialnetwork.featurepost.presentation.personlist.PersonListScreen
import com.fifty.socialnetwork.featurepost.presentation.postdetail.PostDetailScreen
import com.fifty.socialnetwork.featureprofile.presentation.profile.ProfileScreen
import com.fifty.socialnetwork.featureauth.presentation.register.RegisterScreen
import com.fifty.socialnetwork.featureprofile.presentation.search.SearchScreen
import com.fifty.socialnetwork.featureauth.presentation.splash.SplashScreen
import com.fifty.socialnetwork.featurechat.presentation.message.MessageScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = Screen.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.MainFeedScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                imageLoader = imageLoader
            )
        }
        composable(
            route = Screen.MessageScreen.route + "/{chatId}/{remoteUserId}/{remoteUsername}/{remoteUserProfilePictureUrl}",
            arguments = listOf(
                navArgument("chatId") {
                    type = NavType.StringType
                },
                navArgument("remoteUserId") {
                    type = NavType.StringType
                },
                navArgument("remoteUsername") {
                    type = NavType.StringType
                },
                navArgument("remoteUserProfilePictureUrl") {
                    type = NavType.StringType
                }
            )
        ) {
            val remoteUserName = it.arguments?.getString("remoteUsername")!!
            val remoteUserProfilePictureUrl =
                it.arguments?.getString("remoteUserProfilePictureUrl")!!
            MessageScreen(
                remoteUserName = remoteUserName,
                encodedRemoteUserProfilePictureUrl = remoteUserProfilePictureUrl,
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                imageLoader = imageLoader
            )
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp
            )
        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ProfileScreen(
                userId = it.arguments?.getString("userId"),
                onNavigate = navController::navigate,
                onLogout = {
                    navController.navigate(Screen.LoginScreen.route)
                },
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                imageLoader = imageLoader
            )
        }
        composable(Screen.EditProfileScreen.route + "/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                }
            )) {
            EditProfileScreen(
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                scaffoldState = scaffoldState,
                imageLoader = imageLoader
            )
        }
        composable(
            Screen.PostDetailScreen.route + "/{postId}?shouldShowKeyboard={shouldShowKeyboard}",
            arguments = listOf(
                navArgument(
                    name = "postId"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "shouldShowKeyboard"
                ) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    action = Intent.ACTION_VIEW
                    uriPattern = "https://fazalulabid.com/{postId}"
                }
            )
        ) {
            val shouldShowKeyboard = it.arguments?.getBoolean("shouldShowKeyboard") ?: false
            PostDetailScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                shouldShowKeyboard = shouldShowKeyboard,
                imageLoader = imageLoader
            )
        }
        composable(
            Screen.PersonListScreen.route + "/{parentId}",
            arguments = listOf(
                navArgument("parentId") {
                    type = NavType.StringType
                }
            )
        ) {
            PersonListScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
                onNavigateUp = navController::navigateUp,
                imageLoader = imageLoader
            )
        }
    }
}