package com.avenuecode.bprepared.dagger

import com.avenuecode.bprepared.BaseApp
import com.avenuecode.bprepared.dagger.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (ActivityModule::class),
    (FragmentModule::class),
    (NetworkModule::class),
    (RepoModule::class),
    (ViewModelModule::class),
    (DatabaseModule::class)])

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: BaseApp): Builder

        fun build(): AppComponent
    }

    fun inject(application: BaseApp)
}




/*,
(ActivityModule::class),
(FragmentModule::class),
(DatabaseModule::class),
(RepoModule::class),
(ViewModelModule::class) */