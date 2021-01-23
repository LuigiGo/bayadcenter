package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import com.androidsystems.bayadcenterapp.ui.promos.list.PromoListViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewModelFactoryModule {

    @Provides
    fun providesPromoListViewModelFactory(
        repository: Repository,
        compositeDisposable: CompositeDisposable
    ): PromoListViewModelFactory {
        return PromoListViewModelFactory(repository, compositeDisposable)
    }
}