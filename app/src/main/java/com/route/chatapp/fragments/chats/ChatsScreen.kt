package com.route.chatapp.fragments.chats

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.route.chatapp.R
import com.route.chatapp.fragments.search.SearchScreen
import com.route.chatapp.fragments.search.SearchViewModel
import com.route.chatapp.ui.theme.blue
import com.route.chatapp.utils.ChatAppBar
import com.route.chatapp.utils.ChatTextButton
import com.route.chatapp.utils.NavigationDrawerSheet

@Composable
fun ChatsScreen(
    drawerState: DrawerState,
    onFabClick: () -> Unit,
    onSlideMenuClick: () -> Unit
) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationDrawerSheet {

            }
        }
    ) {

        Scaffold(
            topBar = {
                ChatAppBar(
                    titleResId = R.string.chat_app,
                    shouldDisplaySlideMenuIcon = true,
                    shouldDisplaySearchIcon = true,
                    onSlideMenuClick = { onSlideMenuClick() },
                    onSearchClick = {

                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    containerColor = blue,
                    contentColor = Color.White,
                    modifier = Modifier.size(60.dp),
                    onClick = {
                        onFabClick()
                    }
                ) {
                    Icon(painterResource(R.drawable.ic_add), stringResource(R.string.fab))
                }

            }
        ) { paddingValues ->
            paddingValues

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(R.drawable.pattern),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                val (grid) = createRefs()
                val guideline = createGuidelineFromTop(.14f)
                LazyVerticalGrid(
                    modifier = Modifier.constrainAs(grid) { top.linkTo(guideline) },
                    columns = GridCells.Fixed(2)
                ) {
                    item {
                        ChatTextButton(
                            title = R.string.my_rooms,
                            titleColor = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ) {}
                    }

                    item {
                        ChatTextButton(
                            title = R.string.browse,
                            titleColor = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        ) {}
                    }
                }

            }
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun ChatsScreenPreview() {
    ChatsScreen(rememberDrawerState(initialValue = DrawerValue.Closed),{}, {})
}