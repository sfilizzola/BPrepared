package com.avenuecode.bprepared.view.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.util.Log
import android.view.View
import com.avenuecode.bprepared.R
import com.avenuecode.bprepared.databinding.ActivityLoginBinding
import com.avenuecode.bprepared.utils.Constants
import com.avenuecode.bprepared.view.viewStatus.LoginViewStatus
import com.avenuecode.bprepared.viewmodels.LoginActivityViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.tasks.Task

import javax.inject.Inject


class LoginActivity : BaseAcitvity(), View.OnClickListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(LoginActivityViewModel::class.java) }

    private lateinit var binding: ActivityLoginBinding

    private lateinit var gso: GoogleSignInOptions
    private lateinit var googleApiClient: GoogleApiClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.signInButton.setSize(SignInButton.SIZE_WIDE)
        binding.signInButton.setOnClickListener(this)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        googleApiClient = GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        viewModel.getData().observe(this, Observer {
            it?.let { result ->
                when (result) {
                    is LoginViewStatus.GoogleLogin -> {
                        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(googleApiClient), Constants.GOOGLE_SIGN_IN)
                    }
                    is LoginViewStatus.Error -> {
                        displaySnackbarMessage(binding.root, it.message()!!, null)
                    }
                    is LoginViewStatus.Success -> {
                        it.account()?.let {
                            val intent = Intent(this, MainActivity::class.java)
                            viewModel.handleGoogleAccount(it)
                            startActivity(intent)
                        }

                    }
                }

            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleSignIn(task)
        }
    }

    override fun onClick(view: View?) {
        viewModel.onGoogleLoginClick()
    }


}
