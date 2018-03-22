package ru.techmas.barrier.utils.presenter

import android.content.SharedPreferences

interface IPreferenceHelper

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class PreferenceHelper(private val preferences: SharedPreferences) : IPreferenceHelper {

    companion object {
        private const val PREF_TOKEN_API = "tokenAPI"
        private const val PREF_NUMBER = "number"
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

    internal var number: String? = null
        set(value) {
            preferences.edit().putString(PREF_NUMBER, value).apply()
            field = value
        }
        get() = if (field == null) {
            field = preferences.getString(PREF_NUMBER, "")
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

