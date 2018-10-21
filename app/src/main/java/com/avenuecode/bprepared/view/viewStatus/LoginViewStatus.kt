package com.avenuecode.bprepared.view.viewStatus

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

sealed class LoginViewStatus {
    object GoogleLogin : LoginViewStatus()
    data class Error(val message: String?) : LoginViewStatus()
    data class Success(val account: GoogleSignInAccount?) : LoginViewStatus()

    fun message(): String? {
        return when (this) {
            is Error -> this.message
            else -> null
        }
    }


    fun account(): GoogleSignInAccount? {
        return when (this) {
            is Success -> this.account
            else -> null
        }
    }

}