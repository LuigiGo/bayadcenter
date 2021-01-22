package com.androidsystems.bayadcenterapp.core.di.module

import com.androidsystems.bayadcenterapp.ui.promos.list.PromoListActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindPromoListActivity(): PromoListActivity
}