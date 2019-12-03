package com.airtel.addresssuggestions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject
constructor(val providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = providerMap[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else {
            throw IllegalArgumentException("Can't find provider for ViewModel")
        }
    }
}