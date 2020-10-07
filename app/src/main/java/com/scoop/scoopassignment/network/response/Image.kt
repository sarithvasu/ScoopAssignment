package com.scoop.scoopassignment.network.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    @SerializedName("#text")
    @Expose
    val text: String,
    val size: String
):Parcelable