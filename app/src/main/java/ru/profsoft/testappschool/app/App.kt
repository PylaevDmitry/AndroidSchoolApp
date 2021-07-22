package ru.profsoft.testappschool.app

import android.app.Application
import ru.profsoft.testappschool.app.di.AppComponent
import ru.profsoft.testappschool.app.di.DaggerAppComponent

class App : Application(), AppComponent.ComponentProvider {

    companion object {
        lateinit var INSTANCE: App
            private set
    }

    init {
        INSTANCE = this
    }

    override lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }

}