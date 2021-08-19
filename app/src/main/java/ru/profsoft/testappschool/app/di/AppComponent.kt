package ru.profsoft.testappschool.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.profsoft.testappschool.app.App
import ru.profsoft.testappschool.app.di.module.AppModule
import ru.profsoft.testappschool.app.di.module.NetworkModule
import ru.profsoft.testappschool.app.di.module.PreferenceModule
import ru.profsoft.testappschool.app.di.module.RepositoryModule
import ru.profsoft.testappschool.ui.MainActivity
import ru.profsoft.testappschool.ui.auth.AuthFragment
import ru.profsoft.testappschool.ui.auth.AuthMainFragment
import ru.profsoft.testappschool.ui.auth.SearchFragment
import ru.profsoft.testappschool.ui.main.MainFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        PreferenceModule::class,
        RepositoryModule::class
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

    fun inject(activity:MainActivity)
    fun inject(fragment:AuthMainFragment)
    fun inject(fragment: AuthFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: SearchFragment)

}