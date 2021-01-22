package com.androidsystems.bayadcenterapp.data.entities

import com.google.gson.annotations.SerializedName

data class PromoResponseItem(
    val details: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    val read: Int
)