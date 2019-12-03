package com.airtel.addresssuggestions

import android.app.Activity
import android.app.Application
import com.airtel.addresssuggestions.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class AddressSuggestions : Application(),HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {

        lateinit var app :AddressSuggestions

        fun getInstance(): AddressSuggestions{
            return  app
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        app=this

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


}