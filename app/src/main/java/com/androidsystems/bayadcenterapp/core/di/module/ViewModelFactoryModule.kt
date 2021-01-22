package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.data.network.repository.Repository
import com.androidsystems.bayadcenterapp.ui.promos.list.PromosViewModelFactory
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ViewModelFactoryModule {

    @ContributesAndroidInjector
    abstract fun bindsPromosViewModelFactory(repository: Repository): PromosViewModelFactory
}