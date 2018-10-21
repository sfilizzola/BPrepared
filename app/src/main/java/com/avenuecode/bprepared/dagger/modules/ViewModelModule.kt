package com.avenuecode.bprepared.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.avenuecode.bprepared.dagger.AppViewModelFactory
import com.avenuecode.bprepared.dagger.ViewModelKey
import com.avenuecode.bprepared.viewmodels.LoginActivityViewModel
import com.avenuecode.bprepared.viewmodels.MainActivityViewModel
import com.avenuecode.bprepared.viewmodels.MainListViewModel
import com.avenuecode.bprepared.viewmodels.UserInfoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    internal abstract fun bindLoginActivityViewModel(loginActivityViewModel: LoginActivityViewModel): ViewModel

   @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainListViewModel::class)
    internal abstract fun bindMainListViewModel(mainListViewModel: MainListViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(UserInfoViewModel::class)
    internal abstract fun bindUserInfoViewModel(userInfoViewModel: UserInfoViewModel): ViewModel


    @Binds
    internal abstract fun bindAppViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}