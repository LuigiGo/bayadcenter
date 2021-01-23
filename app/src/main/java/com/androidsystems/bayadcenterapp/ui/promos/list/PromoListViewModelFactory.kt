package com.androidsystems.bayadcenterapp.ui.promos.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import io.reactivex.disposables.CompositeDisposable

class PromoListViewModelFactory(
    private val repository: Repository,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PromoListViewModel(repository, compositeDisposable) as T
    }
}