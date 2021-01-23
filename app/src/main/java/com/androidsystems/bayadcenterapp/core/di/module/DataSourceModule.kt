package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.data.network.base.NetworkServiceApi
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSource
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSourceImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class DataSourceModule {

    @Provides
    fun providesPromosDataSource(
        networkServiceApi: NetworkServiceApi,
        compositeDisposable: CompositeDisposable
    ): PromosDataSource {
        return PromosDataSourceImpl(networkServiceApi, compositeDisposable)
    }
}