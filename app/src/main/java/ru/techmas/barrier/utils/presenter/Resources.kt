package ru.techmas.barrier.utils.presenter

import android.support.annotation.ArrayRes
import android.support.annotation.StringRes

import ru.techmas.barrier.App

/**
 * Date: 04.06.2017
 * Time: 10:53
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
class Resources(private val app: App) {

    fun getString(@StringRes resId: Int): String {
        return app.getString(resId)
    }

    fun getStringArray(@ArrayRes resId: Int): Array<String> {
        return app.resources.getStringArray(resId)
    }

}
