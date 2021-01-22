package com.androidsystems.bayadcenterapp.ui.promos.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidsystems.bayadcenterapp.data.network.repository.Repository

class PromoListViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PromoListViewModel(repository) as T
    }
}