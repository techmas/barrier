package ru.techmas.barrier.utils

import android.app.Activity
import android.util.Log
import android.view.inputmethod.InputMethodManager

import android.content.Context.INPUT_METHOD_SERVICE

/**
 * Date: 04.06.2017
 * Time: 9:56
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
object KeyboardHelper {

    private val TAG = KeyboardHelper::class.java.simpleName
    //@formatter:on

    fun hide(activity: Activity) {
        try {
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            Log.d(TAG, "hide: Keyboard is not found")
        }

    }
}//@formatter:off
