package com.androidsystems.bayadcenterapp.data.network.web.promos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidsystems.bayadcenterapp.core.base.BaseDataSource
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.base.NetworkServiceApi
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import javax.inject.Inject

class PromosDataSourceImpl @Inject constructor(private val networkServiceApi: NetworkServiceApi) : BaseDataSource(),
    PromosDataSource {

    private val _downloadedPromoList = MutableLiveData<Resource<PromoResponse>>()
    override val downloadedPromoList: LiveData<Resource<PromoResponse>>
        get() = _downloadedPromoList

    override suspend fun getPromoList() {
        val response = getResult { networkServiceApi.getPromos() }
        _downloadedPromoList.postValue(response)
    }
}