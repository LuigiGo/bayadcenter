package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import com.androidsystems.bayadcenterapp.ui.promos.list.PromoListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    fun providesPromoListViewModelFactory(repository: Repository): PromoListViewModelFactory {
        return PromoListViewModelFactory(repository)
    }
}