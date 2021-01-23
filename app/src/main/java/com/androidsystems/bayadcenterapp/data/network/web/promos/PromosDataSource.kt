package com.androidsystems.bayadcenterapp.data.network.web.promos

import androidx.lifecycle.LiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse

interface PromosDataSource {

    val downloadedPromoList: LiveData<Resource<PromoResponse>>
    val updatedPromoItem: LiveData<Resource<PromoItem>>
    val deletedPromoItem: LiveData<Resource<PromoItem>>

    fun getPromoList()

    fun updatePromo(promoItem: PromoItem)

    fun deletePromo(promoItem: PromoItem)

}