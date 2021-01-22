package com.androidsystems.bayadcenterapp.core

import com.androidsystems.bayadcenterapp.core.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerApplicationComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }
}