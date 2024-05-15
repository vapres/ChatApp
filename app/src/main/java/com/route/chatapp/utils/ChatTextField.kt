package com.route.chatapp.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatapp.R
import com.route.chatapp.ui.theme.Poppins
import com.route.chatapp.ui.theme.black
import com.route.chatapp.ui.theme.blue
import com.route.chatapp.ui.theme.greyLight
import com.route.chatapp.ui.theme.indicatorDisabledColor
import com.route.chatapp.ui.theme.labelColor
import com.route.chatapp.ui.theme.placeholderColor
import com.route.chatapp.ui.theme.textFieldInputColor

@Composable
fun ChatTextField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    errorState: MutableState<String?>? = null,
    label: Int? = null,
    isPassword: Boolean = false,
    placeholder: Int? = null
) {
    TextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = {
            if (label != null)
                Text(
                    text = stringResource(label),
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = labelColor
                )
        },

        placeholder = {
            if (placeholder != null)
                Text(
                    text = stringResource(placeholder),
                    fontFamily = Poppins,
                    color = placeholderColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
        },
        supportingText = {
            if (errorState?.value != null) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = errorState.value!!,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (errorState?.value != null) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(R.drawable.errror),
                    contentDescription = stringResource(R.string.error),
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },

        keyboardOptions =
        if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password)
        else KeyboardOptions(),
        visualTransformation =
        if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None,

        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedTextColor = textFieldInputColor,
            unfocusedTextColor = textFieldInputColor,
            disabledIndicatorColor = indicatorDisabledColor,
            focusedIndicatorColor = blue,
            cursorColor = blue,
            errorIndicatorColor = MaterialTheme.colorScheme.error
        ),
        modifier = modifier,
        maxLines = 1,
        singleLine = true
    )
}