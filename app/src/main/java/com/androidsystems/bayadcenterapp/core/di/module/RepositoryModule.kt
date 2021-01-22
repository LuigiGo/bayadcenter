package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import com.androidsystems.bayadcenterapp.data.network.repository.RepositoryImpl
import com.androidsystems.bayadcenterapp.data.network.web.promos.PromosDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesRepository(reportsDataSource: PromosDataSource): Repository {
        return RepositoryImpl(reportsDataSource)
    }
}