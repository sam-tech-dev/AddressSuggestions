package com.airtel.addresssuggestions.data.remote

import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import io.reactivex.Observable
import io.reactivex.Single

interface ApiHelper{
    fun getSuggestedAddresses(queryString:String, city: String): Observable<SuggestedAddressResponse>
}