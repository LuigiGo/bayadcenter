package com.androidsystems.bayadcenterapp.core.di.module

import android.content.Context
import com.androidsystems.bayadcenterapp.core.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindApplicationContext(app: App): Context
}