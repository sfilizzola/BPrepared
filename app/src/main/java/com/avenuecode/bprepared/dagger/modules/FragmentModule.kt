package com.avenuecode.bprepared.dagger.modules

import com.avenuecode.bprepared.view.fragment.MainListFragment
import com.avenuecode.bprepared.view.fragment.UserInfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    abstract fun bindMainListFragment(): MainListFragment

    @ContributesAndroidInjector
    abstract fun bindUserInfoFragment(): UserInfoFragment


}