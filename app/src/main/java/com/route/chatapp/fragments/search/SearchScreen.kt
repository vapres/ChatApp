package com.route.chatapp.fragments.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatapp.R
import com.route.chatapp.ui.theme.blue
import com.route.chatapp.ui.theme.greyLight
import com.route.chatapp.ui.theme.indicatorDisabledColor
import com.route.chatapp.utils.ChatTextField

@Composable
fun SearchScreen(viewModel: SearchViewModel = viewModel()) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = viewModel.searchQuery,
        onValueChange = {
            viewModel.searchQuery = it
        },

        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(R.string.search_icon),
                tint = blue,
                modifier = Modifier
                    .size(16.dp)
                    .clickable {
                        focusManager.clearFocus()
                    }
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                color = greyLight
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),

        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {

        }),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth().statusBarsPadding().padding(8.dp)
            .background(Color.White, RoundedCornerShape(30.dp))
            .focusRequester(viewModel.focusRequester)
            .onFocusChanged { viewModel.isFocused = true }
    )

}


@Preview(showSystemUi = true)
@Composable
private fun SearchPreview() {
    SearchScreen()
}