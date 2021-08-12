package ru.profsoft.testappschool.app.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.profsoft.testappschool.data.storage.Pref

@Module
class PreferenceModule {
    @Provides
    fun providePref(
        context: Context):
            Pref = Pref(context)

}