package com.route.chatapp.fragments.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatapp.model.AppUser
import com.route.chatapp.model.Firestore

class SignupViewModel : ViewModel() {
    val emailState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordState = mutableStateOf("")
    val passwordErrorState = mutableStateOf<String?>(null)
    val firstNameState = mutableStateOf("")
    val firstNameErrorState = mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val events = mutableStateOf<SignupViewEvent>(SignupViewEvent.Idle)
    private val auth = Firebase.auth

    private fun validateTextField(): Boolean {
        if (emailState.value.isBlank() || emailState.value.isEmpty()) {
            emailErrorState.value = "required"
            return false
        } else
            emailErrorState.value = null

        if (passwordState.value.isBlank() || passwordState.value.isEmpty()) {
            passwordErrorState.value = "required"
            return false
        } else
            passwordErrorState.value = null

        if (firstNameState.value.isBlank() || firstNameState.value.isEmpty()) {
            firstNameErrorState.value = "required"
            return false
        } else
            firstNameErrorState.value = null

        return true
    }

    fun authenticateUser(){
        if(!validateTextField()) return
        isLoading = true

        auth
            .createUserWithEmailAndPassword(emailState.value, passwordState.value)
            .addOnCompleteListener { task ->

                isLoading = false
                if (!task.isSuccessful) {
                    Log.e("TAG", "${task.exception?.message}")
                    errorMessage.value = task.exception?.message
                    return@addOnCompleteListener
                }
                val uid = task.result.user?.uid

                firebaseAddUser(uid!!)
            }

    }

    private fun firebaseAddUser(uid:String) {
        val user = AppUser(uid, uFirstName = firstNameState.value, uEmail = emailState.value)
        Firestore.addUser(
            user,
            onSuccessListener = {
                // Dismiss Loading
                events.value = SignupViewEvent.NavigateToHome(user)
            },
            onFailureListener = {
                errorMessage.value = "${it.message}"
            }
        )
    }

}