package com.route.chatapp.fragments.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel

class SearchViewModel:ViewModel() {

    var searchQuery by mutableStateOf("")
    val focusRequester = FocusRequester()
    var isFocused by mutableStateOf(false)
}