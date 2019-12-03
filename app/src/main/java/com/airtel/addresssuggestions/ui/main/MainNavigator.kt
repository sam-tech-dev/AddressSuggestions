package com.airtel.addresssuggestions.ui.main

import com.airtel.addresssuggestions.data.models.SuggestedAddressResponse

interface MainNavigator {

    fun handleError(throwable: Throwable)

    fun updateSuggestionList(response: SuggestedAddressResponse)

}