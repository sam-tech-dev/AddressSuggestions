package com.airtel.addresssuggestions.data.remote

import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequests {

    @GET("compassLocation/rest/address/autocomplete")
    fun getSuggestedAddresses(@Query("queryString") queryString: String, @Query("city") city: String): Observable<SuggestedAddressResponse>

}