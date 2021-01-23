package com.androidsystems.bayadcenterapp.data.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSource

class RepositoryImpl(
    private val promoDataSource: PromosDataSource
) : Repository {

    private val _downloadedPromoList = MutableLiveData<Resource<PromoResponse>>()
    private val _updatedPromoItem = MutableLiveData<Resource<PromoItem>>()
    private val _deletedPromoItem = MutableLiveData<Resource<PromoItem>>()

    init {
        promoDataSource.downloadedPromoList.observeForever {
            _downloadedPromoList.postValue(it)
        }

        promoDataSource.updatedPromoItem.observeForever {
            _updatedPromoItem.postValue(it)
        }

        promoDataSource.deletedPromoItem.observeForever {
            _deletedPromoItem.postValue(it)
        }
    }

    override val downloadedPromos: LiveData<Resource<PromoResponse>>
        get() = _downloadedPromoList

    override val updatedPromoItem: LiveData<Resource<PromoItem>>
        get() = _updatedPromoItem

    override val deletedPromoItem: LiveData<Resource<PromoItem>>
        get() = _deletedPromoItem

    override fun getPromoList() {
        promoDataSource.getPromoList()
    }

    override fun updatePromo(promoItem: PromoItem) {
        promoDataSource.updatePromo(promoItem)
    }

    override fun deletePromo(promoItem: PromoItem) {
        promoDataSource.deletePromo(promoItem)
    }
}