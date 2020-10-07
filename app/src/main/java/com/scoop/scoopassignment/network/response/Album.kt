package com.scoop.scoopassignment.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val artist: String,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val streamable: String,
    val url: String
):Parcelable