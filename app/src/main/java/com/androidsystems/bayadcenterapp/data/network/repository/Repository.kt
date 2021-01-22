package com.androidsystems.bayadcenterapp.data.network.repository

import androidx.lifecycle.LiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse

interface Repository {

    suspend fun getPromoList(): LiveData<out Resource<PromoResponse>>
}