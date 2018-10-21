package com.avenuecode.bprepared.viewmodels

import android.arch.lifecycle.MutableLiveData
import com.avenuecode.bprepared.repos.DataRepository
import com.avenuecode.bprepared.view.viewStatus.LoginViewStatus
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import timber.log.Timber
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(private var repository: DataRepository) :  BaseViewModel() {

    private var data = MutableLiveData<LoginViewStatus>()


    fun onGoogleLoginClick(){
       data.postValue(LoginViewStatus.GoogleLogin)
    }


    fun getData():MutableLiveData<LoginViewStatus> = data

    fun handleSignIn(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            data.postValue(LoginViewStatus.Success(account))

        } catch (error:ApiException) {
            data.postValue(LoginViewStatus.Error(error.message))
        }
    }

    fun handleGoogleAccount(account: GoogleSignInAccount) {
        //TODO - handle user account
    }
}