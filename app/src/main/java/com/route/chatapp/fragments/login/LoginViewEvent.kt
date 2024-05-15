package com.route.chatapp.fragments.login

import com.route.chatapp.model.AppUser

sealed class LoginViewEvent {
    object Idle : LoginViewEvent() //Initial State Logged out
    // object InProgress : LoginViewEvent() // Show Circular Progress Bar
    // data class Error(val errorMessage: String) : LoginViewEvent() // Show Error Message in Snack Bar

    object Registration : LoginViewEvent()  // Navigate to Sign up Screen
    data class LoggedIn(val user: AppUser) : LoginViewEvent() // Navigate to Home Screen

}