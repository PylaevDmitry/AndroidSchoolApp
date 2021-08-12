package ru.profsoft.testappschool.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Singleton

@Singleton
class EncryptedPreferences (
    context: Context
) {

    companion object {
        const val FILE_NAME = "TestAppSchoolPreference"
    }

    private val mainKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val sharedPrefFile: String = FILE_NAME
    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        sharedPrefFile,
        mainKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}