package ru.techmas.barrier.utils.presenter

import android.content.SharedPreferences

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class PreferenceHelper(private val preferences: SharedPreferences) {

    companion object {
        private const val PREF_TOKEN_API = "tokenAPI"
    }

    internal var token: String? = null
        set(value) {
            preferences.edit().putString(PREF_TOKEN_API, value).apply()
            field = value
        }
        get() = if (field == null) {
            field = preferences.getString(PREF_TOKEN_API, "")
            field
        } else {
            field
        }

    val isFirstRun: Boolean
        get() = !preferences.contains(PREF_TOKEN_API)

    fun exit() {
        preferences.edit().clear().apply()
    }

}

