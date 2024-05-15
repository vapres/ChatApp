package com.route.chatapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatapp.R
import com.route.chatapp.ui.theme.Poppins


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatAppBar(
    titleResId: Int? = null,
    titleString: String? = "",
    shouldDisplaySlideMenuIcon: Boolean = false,
    shouldDisplayOverflowMenuIcon: Boolean = false,
    shouldDisplaySearchIcon: Boolean = false,
    shouldDisplayBackIcon: Boolean = false,
    onSlideMenuClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onOverflowMenu: (() -> Unit)? = null,
    onBackClick: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text =
                if (titleResId != null)
                    stringResource(titleResId)
                else
                    titleString ?: stringResource(R.string.chat_app),

                fontFamily = Poppins, fontSize = 20.sp,
                fontWeight = FontWeight.Bold, color = Color.White
            )
        },
        navigationIcon = {
            if (shouldDisplaySlideMenuIcon)
                AppBarButton(R.drawable.ic_slide_menu, R.string.slide_menu) {
                    if (onSlideMenuClick != null)
                        onSlideMenuClick()
                }
            else if (shouldDisplayBackIcon)
                AppBarButton(R.drawable.ic_back_arrow, R.string.back_arrow) {
                    if (onBackClick != null)
                        onBackClick()
                }
            else
                Spacer(modifier = Modifier.padding(horizontal = 10.dp).size(25.dp))
        },

        actions = {
            if (shouldDisplaySearchIcon)
                AppBarButton(R.drawable.ic_search, R.string.search_icon) {
                    if (onSearchClick != null)
                        onSearchClick()
                }
            else if (shouldDisplayOverflowMenuIcon)
                AppBarButton(R.drawable.ic_overflow_menu, R.string.overflow_icon) {
                    if (onOverflowMenu != null)
                        onOverflowMenu()
                }
            else
                Spacer(modifier = Modifier.padding(horizontal = 10.dp).size(25.dp))
        },

        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
fun AppBarButton(iconResId: Int, descResId: Int, onClick: () -> Unit) {
    Image(
        painterResource(iconResId),
        contentDescription = stringResource(descResId),
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .size(25.dp)
            .clickable {
                onClick()
            }
    )

}