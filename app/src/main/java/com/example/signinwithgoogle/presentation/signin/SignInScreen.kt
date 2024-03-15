package com.example.signinwithgoogle.presentation.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Box(
       modifier = Modifier
           .fillMaxSize()
           .padding(16.dp),
       contentAlignment = Alignment.Center
    ) {
        Button(onClick = onSignInClick) {
            Text(text = "Sign in with google")
        }
    }
}
//
//    val username = rememberSaveable { mutableStateOf("") }
//    val email = rememberSaveable { mutableStateOf("") }
//    val password = rememberSaveable { mutableStateOf("") }
//    val focusManager = LocalFocusManager.current
//
//    Column(
//        modifier = Modifier
//            .background(color = MaterialTheme.colorScheme.surface)
//            .fillMaxSize()
//            .padding(bottom = 4.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(
//            modifier = Modifier
//                .padding(top = 4.dp, end = 4.dp)
//                .fillMaxWidth(0.7F)
//                .height(60.dp),
//            value = username.value,
//            onValueChange = { text -> onTextChange( text = text, textState = username) },
//            textStyle = TextStyle(fontSize = 18.sp),
//            singleLine = true,
//            placeholder = {
//                Text(
//                    text = "username",
//                    style = TextStyle(fontSize = 18.sp)
//                )
//            },
//            shape = RoundedCornerShape(4.dp),
//        )
//
//        OutlinedTextField(
//            modifier = Modifier
//                .padding(top = 4.dp, end = 4.dp)
//                .fillMaxWidth(0.7F)
//                .height(60.dp),
//            value = email.value,
//            onValueChange = { text -> onTextChange( text = text, textState = email) },
//            textStyle = TextStyle(fontSize = 18.sp),
//            singleLine = true,
//            placeholder = {
//                Text(
//                    text = "email",
//                    style = TextStyle(fontSize = 18.sp)
//                )
//            },
//            shape = RoundedCornerShape(4.dp),
//        )
//
//        OutlinedTextField(
//            modifier = Modifier
//                .padding(top = 4.dp, end = 4.dp)
//                .fillMaxWidth(0.7F)
//                .height(60.dp),
//            value = password.value,
//            onValueChange = { v -> onTextChange(text = v, textState = password) },
//            textStyle = TextStyle(fontSize = 18.sp),
//            singleLine = true,
//            placeholder = {
//                Text(
//                    text = "password",
//                    style = TextStyle(fontSize = 18.sp)
//                )
//            },
//            shape = RoundedCornerShape(4.dp),
//        )
//
//        OutlinedButton(
//            modifier = Modifier
//                .padding(top = 4.dp, start = 0.dp, end = 0.dp)
//                .fillMaxWidth(0.7F)
//                .height(60.dp),
//            shape = RoundedCornerShape(4.dp),
//            onClick = {
//                if (username.value.isNotEmpty() && email.value.isNotEmpty() && password.value.isNotEmpty()) {
//                    focusManager.clearFocus()
//                    onLoginClickListener.userLogin(
//                        username = username.value,
//                        email = email.value,
//                        password = password.value
//                    )
//                }
//            }
//        ) {
//            Text(
//                text = "login",
//                style = TextStyle(fontSize = 18.sp)
//            )
//        }
//
//        OutlinedButton(
//            modifier = Modifier
//                .padding(top = 4.dp, start = 0.dp, end = 0.dp)
//                .fillMaxWidth(0.7F)
//                .height(60.dp),
//            shape = RoundedCornerShape(4.dp),
//            onClick = {
//                focusManager.clearFocus()
//                onGoogleSignInListener.googleSignIn()
//            }
//        ) {
//            Text(
//                text = "login via google",
//                style = TextStyle(fontSize = 18.sp)
//            )
//        }
//    }
//}
//
//fun onTextChange(text: String, textState: MutableState<String>) {
//    if (text.isEmpty()) {
//        textState.value = text
//    } else if (text.isNotEmpty() && text.last() == '\n' && text.first() != ' ' && text.first() != '\n') {
//        textState.value = text
//    } else if (text.isNotEmpty() && (text.first() == ' ' || text.first() == '\n')) {
//        textState.value = ""
//    } else {
//        textState.value = text
//    }
//}
