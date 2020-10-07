package com.scoop.scoopassignment.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OpensearchQuery(
    @SerializedName("#text")
    @Expose
    val text: String,
    val role: String,
    val searchTerms: String,
    val startPage: String
)