package com.androidsystems.bayadcenterapp.data.network.base

import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface NetworkServiceApi {

    @GET(API_PROMO)
    fun getPromos(): Deferred<PromoResponse>
}