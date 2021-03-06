package com.androidsystems.bayadcenterapp.core.di.component

import com.androidsystems.bayadcenterapp.core.App
import com.androidsystems.bayadcenterapp.core.di.module.ActivityBindingModule
import com.androidsystems.bayadcenterapp.core.di.module.AppModule
import com.androidsystems.bayadcenterapp.core.di.module.DataSourceModule
import com.androidsystems.bayadcenterapp.core.di.module.NetworkModule
import com.androidsystems.bayadcenterapp.core.di.module.RepositoryModule
import com.androidsystems.bayadcenterapp.core.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }
}