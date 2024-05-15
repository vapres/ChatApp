package com.route.chatapp.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.route.chatapp.R
import com.route.chatapp.ui.theme.black
import kotlinx.coroutines.CoroutineScope

@Composable
fun NavigationDrawerSheet(onSettingsClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxHeight().fillMaxWidth(.7f)
            .background(Color.Transparent)
            .clickable { onSettingsClick() },
        horizontalArrangement = Arrangement.Start
    ) {
        ChatTextButton(
            title = R.string.settings,
            titleColor = black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun NavigationDrawerPreview() {
    NavigationDrawerSheet({})
}