package com.route.chatapp.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatapp.R
import com.route.chatapp.ui.theme.Poppins
import com.route.chatapp.ui.theme.black
import com.route.chatapp.ui.theme.greyDark

@Composable
fun ChatTextButton(
    modifier: Modifier = Modifier,
    title: Int,
    titleColor: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    onClick: (() -> Unit)? = null

) {
    TextButton(
        onClick = {
            if (onClick != null)
                onClick()
        },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = titleColor
        )
    ) {
        Text(
            text = stringResource(title),
            fontFamily = Poppins,
            fontWeight = fontWeight, fontSize = fontSize, color = titleColor,
        )
    }
}
