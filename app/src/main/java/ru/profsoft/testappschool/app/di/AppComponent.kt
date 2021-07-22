package ru.profsoft.testappschool.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.app.di.module.AppModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    interface ComponentProvider {
        val appComponent: AppComponent
    }

}