package com.androidsystems.bayadcenterapp.ui.promos.list

import androidx.lifecycle.ViewModel
import com.androidsystems.bayadcenterapp.core.utils.lazyDeferred
import com.androidsystems.bayadcenterapp.data.network.repository.Repository

class PromoListViewModel(private val repository: Repository) : ViewModel() {

    val loadPromoList by lazyDeferred {
        repository.getPromoList()
    }
}