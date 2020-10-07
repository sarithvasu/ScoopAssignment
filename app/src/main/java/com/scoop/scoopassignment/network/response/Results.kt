package com.scoop.scoopassignment.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("@attr")
    @Expose
    val attr: Attr,
    val albummatches: Albummatches,
    @SerializedName("opensearch:Query")
    @Expose
    val openSearchQuery: OpensearchQuery,
    @SerializedName("opensearch:temsPerPage")
    @Expose
    val openSearchTemsPerPage: String,
    @SerializedName("opensearch:startIndex")
    @Expose
    val openSearchStartIndex: String,
    @SerializedName("opensearch:totalResults:")
    @Expose
    val openSearchTotalResults: String
)