package com.airtel.addresssuggestions.data.remote


import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class AppApiHelper @Inject constructor(val apiRequests: ApiRequests) : ApiHelper {

    override fun getSuggestedAddresses(queryString: String, city: String): Observable<SuggestedAddressResponse> {
       return apiRequests.getSuggestedAddresses(queryString,city)
    }

}