package com.airtel.addresssuggestions.di.module

import androidx.lifecycle.ViewModel
import com.airtel.addresssuggestions.ViewModelProviderFactory
import com.airtel.addresssuggestions.data.AppDataManager
import com.airtel.addresssuggestions.ui.main.MainViewModel
import com.airtel.addresssuggestions.utils.AppSchedulerProvider
import dagger.Binds
import dagger.multibindings.IntoMap
import dagger.Provides
import dagger.MapKey
import dagger.Module

import javax.inject.Provider

import kotlin.reflect.KClass


@Module
class ViewModelsModule {

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)


     @Provides
     internal fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProviderFactory {
         return ViewModelProviderFactory(providerMap)
     }

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal fun provideMainViewModel(appDataManager: AppDataManager, schedulerProvider: AppSchedulerProvider): ViewModel {
        return MainViewModel(appDataManager,schedulerProvider)
    }
}