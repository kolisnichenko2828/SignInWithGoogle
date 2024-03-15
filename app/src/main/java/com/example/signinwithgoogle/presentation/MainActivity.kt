package com.example.signinwithgoogle.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.signinwithgoogle.presentation.signin.GoogleAuthUiClient
import com.example.signinwithgoogle.presentation.signin.SignInScreen
import com.example.signinwithgoogle.presentation.signin.SignInViewModel
import com.example.signinwithgoogle.presentation.theme.MainScreen
import com.example.signinwithgoogle.presentation.theme.SignInWithGoogleTheme
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SignInWithGoogleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val vm = viewModel<SignInViewModel>()
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "IsSignedIn") {
                        composable("IsSignedIn") {
                            LaunchedEffect(key1 = Unit) {
                                if (googleAuthUiClient.getSignedInUser() == null) {
                                    navController.navigate("SignInScreen")
                                } else {
                                    navController.navigate("MainScreen")
                                    vm.resetState()
                                }
                            }
                        }
                        composable("SignInScreen") {
                            val state by vm.state.collectAsState()
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUiClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            vm.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInIsSuccessful) {
                                if (state.isSignInIsSuccessful) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Sign in successful",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("MainScreen")
                                    vm.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUiClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }
                        composable("MainScreen") {
                            MainScreen()
                        }
                    }
                }
            }
        }
    }
}