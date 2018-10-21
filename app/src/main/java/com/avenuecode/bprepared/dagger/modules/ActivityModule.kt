package com.avenuecode.bprepared.dagger.modules

import com.avenuecode.bprepared.view.activities.LoginActivity
import com.avenuecode.bprepared.view.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}