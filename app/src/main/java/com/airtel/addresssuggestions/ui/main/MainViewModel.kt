package com.airtel.addresssuggestions.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.airtel.addresssuggestions.data.AppDataManager
import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import com.airtel.addresssuggestions.ui.base.BaseViewModel
import com.airtel.addresssuggestions.utils.AppSchedulerProvider
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import java.util.concurrent.TimeUnit


class MainViewModel constructor( val appDataManager: AppDataManager, val schedulerProvider: AppSchedulerProvider ) : BaseViewModel<MainNavigator>() {


    val citiesListData = MutableLiveData<ArrayList<String>>()
    val citySelectedIndexData = MutableLiveData<Int>()

    init {
        val cities = arrayListOf<String>()
        cities.add("Gurgaon")
        cities.add("Mumbai")
        cities.add("Delhi")
        cities.add("kolkata")
        cities.add("Jaipur")

        citiesListData.value = cities
    }

    fun setSearchListener(observable: Observable<String>) {

       compositeDisposable.add( observable.debounce(300, TimeUnit.MILLISECONDS)
            .filter(object : Predicate<String> {
                override fun test(text: String): Boolean {
                    if (text.isEmpty()) {
                        return false
                    } else {
                        return true
                    }
                }
            })
            .distinctUntilChanged()
            .switchMap(Function<String, Observable<SuggestedAddressResponse>> {
                appDataManager.getSuggestedAddresses(it, citiesListData.value!!.get(citySelectedIndexData.value!!))
                    .doOnError {
                        //
                    }
            })
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({ response ->
                   navigator?.updateSuggestionList(response)
                   Log.e("az",response.toString())
                }, { throwable ->
                 //
                }))

    }

}






