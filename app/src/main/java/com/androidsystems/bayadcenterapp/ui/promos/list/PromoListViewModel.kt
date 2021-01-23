package com.androidsystems.bayadcenterapp.ui.promos.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PromoListViewModel(private val repository: Repository, private val compositeDisposable: CompositeDisposable) :
    ViewModel() {


    fun getDownloadedPromos(): LiveData<Resource<PromoResponse>> {
        return repository.downloadedPromos
    }

    fun getUpdatedPromoItem(): LiveData<Resource<PromoItem>> {
        return repository.updatedPromoItem
    }

    fun getDeletedPromoItem(): LiveData<Resource<PromoItem>> {
        return repository.deletedPromoItem
    }

    fun getPromoList() {
        repository.getPromoList()
    }

    fun updatePromo(promoItem: PromoItem) {
        repository.updatePromo(promoItem)
    }

    fun deletePromo(promoItem: PromoItem) {
        repository.deletePromo(promoItem)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}