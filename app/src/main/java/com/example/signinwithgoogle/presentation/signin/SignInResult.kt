package com.example.signinwithgoogle.presentation.signin

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val name: String?,
    val profilePictureUrl: String?
)