package com.androidsystems.bayadcenterapp.data.network.repository

import androidx.lifecycle.LiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse

interface Repository {

    val downloadedPromos: LiveData<Resource<PromoResponse>>
    val updatedPromoItem: LiveData<Resource<PromoItem>>
    val deletedPromoItem: LiveData<Resource<PromoItem>>

    fun getPromoList()
    fun updatePromo(promoItem: PromoItem)
    fun deletePromo(promoItem: PromoItem)
}