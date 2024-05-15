package com.route.chatapp.fragments.login


import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.route.chatapp.R
import com.route.chatapp.fragments.signup.SignupScreen
import com.route.chatapp.ui.theme.Poppins
import com.route.chatapp.ui.theme.black
import com.route.chatapp.ui.theme.blue
import com.route.chatapp.ui.theme.greyDark
import com.route.chatapp.utils.ChatAppBar
import com.route.chatapp.utils.ChatButton
import com.route.chatapp.utils.ChatTextButton
import com.route.chatapp.utils.ChatTextField
import com.route.chatapp.utils.ProgressIndicator

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onLoginClick: () -> Unit,
    onForgetPassword: (() -> Unit)? = null,
    onCreateAccount: () -> Unit
) {

    val event by loginViewModel.events

    Scaffold(
        topBar = {
            ChatAppBar(titleResId = R.string.login)
        },

        ) { paddingValues ->
        paddingValues

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = painterResource(R.drawable.pattern),
                    contentScale = ContentScale.FillBounds
                ),
        ) {
            val (welcome, emailTextField, passwordTextField,
                forgetPassword, loginButton, createMyAccount) = createRefs()

            val topGuideline = createGuidelineFromTop(.38f)

            Text(
                text = stringResource(R.string.welcome_back), fontFamily = Poppins,
                fontSize = 24.sp, fontWeight = FontWeight.Bold, color = black,
                modifier = Modifier
                    .padding(start = 24.dp, bottom = 8.dp)
                    .constrainAs(welcome) { top.linkTo(topGuideline) }
            )

            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(emailTextField) { top.linkTo(welcome.bottom) },

                valueState = loginViewModel.emailState,
                label = R.string.email,
                errorState = loginViewModel.emailErrorState
            )

            ChatTextField(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .constrainAs(passwordTextField) { top.linkTo(emailTextField.bottom) },

                valueState = loginViewModel.passwordState,
                label = R.string.password,
                isPassword = true,
                errorState = loginViewModel.passwordErrorState
            )

            ChatTextButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .constrainAs(forgetPassword) { top.linkTo(passwordTextField.bottom) },
                title = R.string.forgot_password,
                titleColor = black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ) {}

            ChatButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .fillMaxWidth()
                    .constrainAs(loginButton) { top.linkTo(forgetPassword.bottom) },
                title = R.string.login,
                showIcon = true,
                bgColor = blue,
                fgColor = Color.White,
                shape = RoundedCornerShape(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                rowModifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                loginViewModel.authenticateUser()
            }

            ChatTextButton(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .constrainAs(createMyAccount) { top.linkTo(loginButton.bottom) },
                title = R.string.or_create_my_account,
                titleColor = greyDark,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            ) {
                loginViewModel.toRegistration()
            }
        }


    }
    ProgressIndicator(loginViewModel.isLoading)
    TriggerEvents(event, onCreateAccount, onLoginClick)

}

@Composable
private fun TriggerEvents(
    event: LoginViewEvent,
    onCreateAccount: () -> Unit,
    onLoginClick: () -> Unit
) {

    when (event) {
        LoginViewEvent.Idle -> {}

        LoginViewEvent.Registration -> onCreateAccount()

        is LoginViewEvent.LoggedIn -> onLoginClick()
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(onLoginClick = {}, onCreateAccount = {})
}