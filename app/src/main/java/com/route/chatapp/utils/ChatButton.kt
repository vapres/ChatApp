package com.route.chatapp.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.chatapp.R
import com.route.chatapp.ui.theme.Poppins
import com.route.chatapp.ui.theme.blue

@Composable
fun ChatButton(
    modifier: Modifier = Modifier,
    title: Int,
    showIcon: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal,
    shape: Shape,
    bgColor: Color,
    fgColor: Color,
    elevation: Dp? = null,
    fontWeight: FontWeight,
    fontSize: TextUnit,
    rowModifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        colors = ButtonDefaults
            .buttonColors(containerColor = bgColor, contentColor = fgColor),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = elevation ?: 0.dp)
    ) {
        Row(
            modifier = rowModifier,
            horizontalArrangement = horizontalArrangement
        ) {

            Text(
                text = stringResource(title), fontFamily = Poppins,
                fontSize = fontSize, fontWeight = fontWeight
            )

            if (showIcon)
                Icon(
                    painterResource(R.drawable.ic_forward_arrow),
                    contentDescription = stringResource(R.string.forward_arrow),
                    tint = fgColor
                )
        }

    }
}