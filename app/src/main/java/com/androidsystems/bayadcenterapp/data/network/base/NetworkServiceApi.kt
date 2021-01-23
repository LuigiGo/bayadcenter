package com.androidsystems.bayadcenterapp.data.network.base

import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NetworkServiceApi {

    @GET(API_GET_PROMO)
    fun getPromos(): Single<PromoResponse>

    @PUT(API_UPDATE_DELETE_PROMO)
    fun updatePromo(
        @Path("promo_id") promoId: String,
        @Body promoItem: PromoItem
    ): Single<ResponseBody>

    @DELETE(API_UPDATE_DELETE_PROMO)
    fun deletePromo(
        @Path("promo_id") promoId: String,
    ): Single<ResponseBody>
}