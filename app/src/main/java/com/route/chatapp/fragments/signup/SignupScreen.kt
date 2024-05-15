package com.route.chatapp.fragments.signup

import android.hardware.TriggerEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatapp.R
import com.route.chatapp.ui.theme.greyLight
import com.route.chatapp.utils.ChatAppBar
import com.route.chatapp.utils.ChatButton
import com.route.chatapp.utils.ChatTextField
import com.route.chatapp.utils.ProgressIndicator

@Composable
fun SignupScreen(
    signupViewModel: SignupViewModel = viewModel(),
    onBackArrowCLick: () -> Unit,
    onSignupClick: () -> Unit
) {
    val event by signupViewModel.events
//    val emailErrorState = signupViewModel.emailErrorState
//    val firstNameErrorState = signupViewModel.firstNameErrorState
//    val passwordErrorState = signupViewModel.passwordErrorState

    Scaffold(
        topBar = {
            ChatAppBar(
                titleResId = R.string.create_account,
                shouldDisplayBackIcon = true,
                onBackClick = onBackArrowCLick
            )
        },

        ) { paddingValues ->
        paddingValues

        ConstraintLayout(
            modifier = Modifier.fillMaxSize().paint(
                painter = painterResource(R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            )
        ) {
            val topGuideline = createGuidelineFromTop(.4f)
            val bottomGuideline = createGuidelineFromBottom(.2f)
            val (nameTextField, emailTextField, passTextField, createButton) = createRefs()

            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(nameTextField) { top.linkTo(topGuideline) },
                valueState = signupViewModel.firstNameState,
                label = R.string.first_name,
                errorState = signupViewModel.firstNameErrorState
            )

            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(emailTextField) { top.linkTo(nameTextField.bottom) },
                valueState = signupViewModel.emailState,
                label = R.string.e_mail_address,
                errorState = signupViewModel.emailErrorState
            )

            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(passTextField) { top.linkTo(emailTextField.bottom) },
                valueState = signupViewModel.passwordState,
                label = R.string.password,
                isPassword = true,
                errorState = signupViewModel.passwordErrorState
            )

            ChatButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .constrainAs(createButton) { top.linkTo(bottomGuideline) },
                title = R.string.create_account,
                showIcon = true,
                bgColor = Color.White,
                fgColor = greyLight,
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                rowModifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                signupViewModel.authenticateUser()
            }
        }
    }

    ProgressIndicator(signupViewModel.isLoading)
    TriggerEvents(signupViewModel.events.value, onSignupClick)
}

@Composable
private fun TriggerEvents(event: SignupViewEvent, onSignupClick: () -> Unit) {
    when (event) {
        SignupViewEvent.Idle -> {}
        is SignupViewEvent.NavigateToHome -> onSignupClick()
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignupScreenPreview() {
    SignupScreen(viewModel(), {}, {})
}