package ru.techmas.barrier.utils

import android.app.Activity
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

import ru.techmas.barrier.R
import ru.techmas.barrier.activities.BaseActivity
import ru.techmas.barrier.fragments.BaseFragment

/**
 * Date: 04.06.2017
 * Time: 10:00
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
object Navigator {


    private val FRAGMENT_TRANSITION = FragmentTransaction.TRANSIT_FRAGMENT_OPEN
    var isFragmentAnimationDisabled = false
    //@formatter:on

    fun startActivity(applicationContext: Activity,
                      activityClass: Class<out BaseActivity>,
                      clearBackStack: Boolean) {
        val intent = Intent(applicationContext, activityClass)
        if (clearBackStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        applicationContext.startActivity(intent)
        applicationContext.overridePendingTransition(R.anim.no_animation, R.anim.no_animation)
    }


    fun startActivityForResult(activity: Activity,
                               activityClass: Class<out BaseActivity>,
                               requestCode: Int) {
        val intent = Intent(activity, activityClass)
        activity.startActivityForResult(intent, requestCode)
        activity.overridePendingTransition(R.anim.no_animation, R.anim.no_animation)
    }


    fun startFragment(fragment: BaseFragment,
                      fm: FragmentManager,
                      @IdRes containerId: Int) {
        fm.beginTransaction()
                .replace(containerId, fragment)
                .setTransition(FRAGMENT_TRANSITION)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
    }


    fun startFragment(fragment: BaseFragment,
                      fm: FragmentManager,
                      @IdRes containerId: Int,
                      addToBackStack: Boolean) {
        if (addToBackStack)
            startFragment(fragment, fm, containerId)
        else
            startFragmentWithoutBackStack(fragment, fm, containerId)
    }

    private fun startFragmentWithoutBackStack(fragment: BaseFragment,
                                              fm: FragmentManager,
                                              @IdRes containerId: Int) {
        fm.beginTransaction()
                .setTransition(FRAGMENT_TRANSITION)
                .replace(containerId, fragment)
                .commit()
    }

    fun startFragment(fragment: BaseFragment,
                      fm: FragmentManager,
                      @IdRes containerId: Int,
                      addToBackStack: Boolean,
                      clearBackStack: Boolean,
                      activityIsFinishing: Boolean) {

        if (clearBackStack && activityIsFinishing) clearBackStack(fm)
        if (addToBackStack) {
            startFragment(fragment, fm, containerId)
        } else
            startFragmentWithoutBackStack(fragment, fm, containerId)
    }

    private fun clearBackStack(fm: FragmentManager) {
        isFragmentAnimationDisabled = true
        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        isFragmentAnimationDisabled = false
    }

}//@formatter:off
