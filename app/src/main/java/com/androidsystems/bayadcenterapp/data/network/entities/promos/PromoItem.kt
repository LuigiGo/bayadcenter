package com.androidsystems.bayadcenterapp.data.network.entities.promos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PromoItem(
    @Expose
    val details: String,
    @Expose(serialize = false)
    @SerializedName("_id")
    val id: String,
    @Expose
    @SerializedName("image_url")
    val imageUrl: String,
    @Expose
    val name: String,
    @Expose
    var read: Int
)