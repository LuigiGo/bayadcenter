package com.androidsystems.bayadcenterapp.data.network.web.promos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidsystems.bayadcenterapp.core.base.BaseDataSource
import com.androidsystems.bayadcenterapp.core.utils.Resource
import com.androidsystems.bayadcenterapp.data.network.base.NetworkServiceApi
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoItem
import com.androidsystems.bayadcenterapp.data.network.entities.promos.PromoResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PromosDataSourceImpl @Inject constructor(
    private val networkServiceApi: NetworkServiceApi,
    private val compositeDisposable: CompositeDisposable
) : BaseDataSource(),
    PromosDataSource {

    private val _downloadedPromoList = MutableLiveData<Resource<PromoResponse>>()
    private val _updatedPromoItem = MutableLiveData<Resource<PromoItem>>()
    private val _deletedPromoItem = MutableLiveData<Resource<PromoItem>>()

    override val downloadedPromoList: LiveData<Resource<PromoResponse>>
        get() = _downloadedPromoList

    override val updatedPromoItem: LiveData<Resource<PromoItem>>
        get() = _updatedPromoItem

    override val deletedPromoItem: LiveData<Resource<PromoItem>>
        get() = _deletedPromoItem

    override fun getPromoList() {
        compositeDisposable.add(
            networkServiceApi.getPromos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _downloadedPromoList.postValue(Resource.success(it))
                }, {
                    _downloadedPromoList.postValue(Resource.error(it.message, null))
                })
        )
    }

    override fun updatePromo(promoItem: PromoItem) {
        compositeDisposable.add(
            networkServiceApi.updatePromo(promoItem.id, promoItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _updatedPromoItem.postValue(Resource.success(promoItem))
                }, {
                    _updatedPromoItem.postValue(Resource.error(it.message, null))
                })
        )
    }

    override fun deletePromo(promoItem: PromoItem) {
        compositeDisposable.add(
            networkServiceApi.deletePromo(promoItem.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _deletedPromoItem.postValue(Resource.success(promoItem))
                }, {
                    _deletedPromoItem.postValue(Resource.error(it.message, null))
                })
        )
    }
}