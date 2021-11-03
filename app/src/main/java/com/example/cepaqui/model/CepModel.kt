package com.example.cepaqui.model

import com.google.gson.annotations.SerializedName

data class CepModel(
    @SerializedName("address")
    val address: String,
    @SerializedName("address_name")
    val addressName: String,
    @SerializedName("address_type")
    val addressType: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("state")
    val state: String
)
