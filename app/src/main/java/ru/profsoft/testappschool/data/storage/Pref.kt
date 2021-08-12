package ru.profsoft.testappschool.data.storage

import android.content.Context

class Pref (private val context: Context) {
    private fun getEncryptedSharedPreferences() = EncryptedPreferences(context)

    private fun getSharedPreferences(prefName: String) =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private val profilePrefs by lazy { getEncryptedSharedPreferences() }

    val mainPrefs by lazy { getSharedPreferences(MAIN_PREFS) }

    var isAuth: Boolean
        get() = mainPrefs.getBoolean(IS_AUTH, false)
        set(value) {
            mainPrefs.edit().putBoolean(IS_AUTH, value).apply()
        }

    var isGuestAuth: Boolean
        get() = mainPrefs.getBoolean(IS_GUEST_AUTH, false)
        set(value) {
            mainPrefs.edit().putBoolean(IS_GUEST_AUTH, value).apply()
        }

    var token: String
        get() = profilePrefs.sharedPreferences.getString(TOKEN, "")!!
        set(value) {
            profilePrefs.sharedPreferences.edit().putString(TOKEN, value).apply()
        }

    companion object {
        private const val MAIN_PREFS = "main_prefs"
        private const val IS_AUTH = "is_auth"
        private const val IS_GUEST_AUTH = "is_guest_auth"
        private const val TOKEN = "token"
    }

}