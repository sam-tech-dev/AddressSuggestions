package com.airtel.addresssuggestions.data


import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import com.airtel.addresssuggestions.data.remote.AppApiHelper
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AppDataManager @Inject constructor(val appApiHelper: AppApiHelper) : DataManager {

    override fun getSuggestedAddresses(queryString: String, city: String): Observable<SuggestedAddressResponse> {
        return appApiHelper.getSuggestedAddresses(queryString,city)
    }

}