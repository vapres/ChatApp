package com.route.chatapp.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.route.chatapp.ui.theme.blue


@Composable
fun ProgressIndicator(loading: Boolean) {
    if (loading)
        Dialog(onDismissRequest = {}) {
            CircularProgressIndicator(
                modifier = Modifier.background(Color.White).padding(48.dp),
                color = blue
            )
        }

}
