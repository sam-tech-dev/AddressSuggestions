package com.airtel.addresssuggestions.data.models
import com.google.gson.annotations.SerializedName


data class SuggestedAddressResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("requestId")
    val requestId: String
)

data class Data(
    @SerializedName("addressList")
    val addressList: MutableList<Address>,
    @SerializedName("autoCompleteRequestString")
    val autoCompleteRequestString: String
)

data class Address(
    @SerializedName("addressString")
    val addressString: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("pinCode")
    val pinCode: String
){
    override fun toString(): String {
        return addressString
    }
}