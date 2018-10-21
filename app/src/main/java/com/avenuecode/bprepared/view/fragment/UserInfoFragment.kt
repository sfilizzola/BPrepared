package com.avenuecode.bprepared.view.fragment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avenuecode.bprepared.R

import com.avenuecode.bprepared.databinding.FragmentUserInfoBinding
import com.avenuecode.bprepared.viewmodels.UserInfoViewModel
import javax.inject.Inject

class UserInfoFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(UserInfoViewModel::class.java) }

    private lateinit var binding: FragmentUserInfoBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info, container, false)
        binding.viewModel = viewModel

        return binding.root

    }

}