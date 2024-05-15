package com.route.chatapp.fragments.signup

import com.route.chatapp.model.AppUser

sealed class SignupViewEvent() {
    object Idle : SignupViewEvent()
    data class NavigateToHome(val user: AppUser) : SignupViewEvent()
}