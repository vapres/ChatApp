package com.route.chatapp.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.graphics.Color
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.chatapp.fragments.chats.ChatsScreen
import com.route.chatapp.fragments.create.CreateRoomScreen
import com.route.chatapp.fragments.login.LoginScreen
import com.route.chatapp.fragments.signup.SignupScreen
import com.route.chatapp.ui.theme.ChatAppTheme
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)
        setContent {
            ChatAppTheme {

                HomeScreen()

            }
        }
    }
}

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable(route = "login") {
            LoginScreen(
                onLoginClick = {
                    navController.navigate("chats")
                },
                onCreateAccount = {
                    navController.navigate("signup")
                }
            )
        }

        composable(route = "signup") {
            SignupScreen(
                onBackArrowCLick = {
                    navController.navigate("login")
                },
                onSignupClick = {
                    navController.navigate("chats")
                }
            )
        }

        composable(route = "chats") {
            ChatsScreen(
                drawerState = drawerState,
                onFabClick = {
                    navController.navigate("createRoom")
                },
                onSlideMenuClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            )
        }

        composable(route = "createRoom") {
            CreateRoomScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ChatAppTheme {
        HomeScreen()
    }
}