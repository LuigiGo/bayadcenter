package com.androidsystems.bayadcenterapp.core.di.module

import android.content.Context
import com.androidsystems.bayadcenterapp.core.App
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppModule {


    @Provides
    fun providesApplicationContext(app: App): Context{
        return app.applicationContext
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable{
        return CompositeDisposable()
    }
}