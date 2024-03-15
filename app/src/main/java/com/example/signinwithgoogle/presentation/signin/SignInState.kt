package com.example.signinwithgoogle.presentation.signin

data class SignInState(
    val isSignInIsSuccessful: Boolean = false,
    val signInError: String? = null
)