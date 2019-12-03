package com.airtel.addresssuggestions.di.component

import android.app.Application
import com.airtel.addresssuggestions.AddressSuggestions
import com.airtel.addresssuggestions.di.builder.ActivityBuilder
import com.airtel.addresssuggestions.di.module.AppModule
import com.airtel.addresssuggestions.di.module.ViewModelsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ViewModelsModule::class, ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: AddressSuggestions)
}