package ru.techmas.barrier.utils.presenter

import android.content.SharedPreferences

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class TokenHelper(private val preferences: SharedPreferences) {


    companion object {
        private val PREF_TOKEN_API = "tokenAPI"
    }

    internal var token: String? = null

    fun getToken(): String? {
        if (token == null) token = preferences.getString(PREF_TOKEN_API, "")
        return token
    }

    fun setToken(token: String) {
        this.token = token
        preferences.edit().putString(PREF_TOKEN_API, token).apply()
    }

    val isFirstRun: Boolean
        get() = !preferences.contains(PREF_TOKEN_API)

    fun exit() {
        preferences.edit().clear().apply()
    }


}

