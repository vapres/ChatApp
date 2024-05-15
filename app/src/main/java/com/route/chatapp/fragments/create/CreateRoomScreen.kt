package com.route.chatapp.fragments.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
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
import com.route.chatapp.R
import com.route.chatapp.ui.theme.Poppins
import com.route.chatapp.ui.theme.blue
import com.route.chatapp.ui.theme.createRoomTxtColor
import com.route.chatapp.ui.theme.outLinedTextFieldColor
import com.route.chatapp.ui.theme.placeholderColor
import com.route.chatapp.utils.ChatAppBar
import com.route.chatapp.utils.ChatButton
import com.route.chatapp.utils.ChatTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRoomScreen(vm: CreateRoomViewModel = viewModel()) {

    Scaffold(
        topBar = {
            ChatAppBar(
                titleResId = R.string.chat_app,
                shouldDisplayBackIcon = true
            )
        }
    ) { paddingValues ->
        paddingValues

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.pattern),
                    contentScale = ContentScale.FillBounds
                ),

            ) {
            val topGuideline = createGuidelineFromTop(.14f)
            val (card) = createRefs()

            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .constrainAs(card) {
                        top.linkTo(topGuideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
//                elevation = CardDefaults.cardElevation(8.dp),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                shape = RoundedCornerShape(16.dp)
            ) {

                Text(
                    text = stringResource(R.string.create_new_room),
                    color = createRoomTxtColor,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 8.dp, bottom = 20.dp)
                )

                Image(
                    painter = painterResource(R.drawable.group),
                    contentDescription = stringResource(R.string.room)
                )

                ChatTextField(
                    valueState = vm.roomNameState,
                    placeholder = R.string.enter_room_name,
                    modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
                )


                ExposedDropdownMenuBox(
                    expanded = vm.isExpanded,
                    onExpandedChange = {
                        vm.isExpanded = it
                    },
                ) {

                    OutlinedTextField(
                        value = vm.selectedCategory,
                        onValueChange = {
                            vm.selectedCategory = it
                        },
                        readOnly = true,
                        placeholder = {
                            Text(
                                text = stringResource(R.string.select_room_category),
                                color = placeholderColor
                            )
                        },

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedBorderColor = blue,
                            unfocusedBorderColor = outLinedTextFieldColor
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(vm.isExpanded)
                        },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = vm.isExpanded,
                        onDismissRequest = {
                            vm.isExpanded = false
                        }
                    ) {
                        vm.categories.forEach() { category ->
                            DropdownMenuItem(
                                onClick = {
                                    vm.selectedCategory = category
                                    vm.isExpanded = false
                                },
                                text = {
                                    Text(text = category)
                                },

//                                colors = MenuItemColors(
//
//                                ),
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }

                    }

                }

                ChatTextField(
                    valueState = vm.roomDescState,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = R.string.enter_room_description,
                )

                ChatButton(
                    title = R.string.create,
                    horizontalArrangement = Arrangement.Center,
                    bgColor = blue,
                    fgColor = Color.White,
                    shape = RoundedCornerShape(35.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
                        .fillMaxWidth(),
                    rowModifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {}
            }

        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun CreateRoomPreview() {
    CreateRoomScreen()
}