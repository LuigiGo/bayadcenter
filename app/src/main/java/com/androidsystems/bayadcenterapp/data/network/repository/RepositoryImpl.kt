package com.androidsystems.bayadcenterapp.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val promoDataSource: PromosDataSource) : Repository {

    private val _downloadedPromoList = MutableLiveData<Resource<PromoResponse>>()

    init {
        promoDataSource.downloadedPromoList.observeForever { response ->
            _downloadedPromoList.postValue(response)
        }
    }

    override suspend fun getPromoList(): LiveData<out Resource<PromoResponse>> {
        promoDataSource.getPromoList()
        return withContext(Dispatchers.IO) {
            return@withContext _downloadedPromoList
        }
    }
}