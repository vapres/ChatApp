package com.route.chatapp.fragments.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateRoomViewModel : ViewModel() {
    val roomNameState = mutableStateOf("")
    val roomDescState = mutableStateOf("")
    var isExpanded by mutableStateOf(false)
    val categories = listOf("Movies", "Music", "Sports")
    var selectedCategory by mutableStateOf("")
}