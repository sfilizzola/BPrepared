package com.avenuecode.bprepared

import android.app.Activity
import android.app.Application
import android.util.Log
import com.avenuecode.bprepared.dagger.DaggerAppComponent
import com.avenuecode.bprepared.models.CurrentUser
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import timber.log.Timber
import java.util.*
import javax.inject.Inject

class BaseApp : Application(), HasActivityInjector{

    companion object {
        var currentUser:CurrentUser? = null
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)


        if (BuildConfig.DEBUG){
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(Locale.getDefault(), "(%s:%s)", element.fileName, element.lineNumber)
                }
            })
        } else {
            Timber.plant(object : Timber.DebugTree(){
                override fun isLoggable(tag: String?, priority: Int): Boolean {
                    return priority == Log.WARN || priority == Log.ERROR
                }
            })
        }
    }
}