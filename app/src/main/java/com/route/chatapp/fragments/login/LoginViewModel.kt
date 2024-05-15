package com.route.chatapp.fragments.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.route.chatapp.model.AppUser
import com.route.chatapp.model.Firestore

class LoginViewModel : ViewModel() {

    val events = mutableStateOf<LoginViewEvent>(LoginViewEvent.Idle)

    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val emailErrorState = mutableStateOf<String?>(null)
    val passwordErrorState = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)
    val auth = Firebase.auth

    fun toRegistration() {
        events.value = LoginViewEvent.Registration
    }

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

        return true
    }

    fun authenticateUser(){
        if(!validateTextField()) return

        isLoading = true

        auth.signInWithEmailAndPassword(emailState.value, passwordState.value)
            .addOnCompleteListener {task->
                isLoading = false

                if(!task.isSuccessful){
                    errorMessage.value = task.exception?.message
                    Log.e("TAG", "${task.exception?.message}")
                    return@addOnCompleteListener
                }
                val uid = task.result.user?.uid
                firestoreGetUser(uid!!)
            }
    }

    private fun firestoreGetUser(uid:String) {
        Firestore.getUser(
            uid,
            onSuccessListener = {
                val user = it.toObject(AppUser::class.java)
                if(user != null)
                     events.value = LoginViewEvent.LoggedIn(user)
            },
            onFailureListener = {
                errorMessage.value = "${it.message}"
                events.value = LoginViewEvent.Idle
            }
        )

    }
}

