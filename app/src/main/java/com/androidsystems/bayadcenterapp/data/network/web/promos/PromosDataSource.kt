package com.androidsystems.bayadcenterapp.data.network.web.promos

import androidx.lifecycle.LiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse

interface PromosDataSource {

    val downloadedPromoList: LiveData<Resource<PromoResponse>>

    suspend fun getPromos()
}