package ru.techmas.barrier.fragments

import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.animation.Animation

import com.arellomobile.mvp.MvpAppCompatFragment
import ru.techmas.barrier.R

import ru.techmas.barrier.activities.BaseActivity
import ru.techmas.barrier.interfaces.utils_view.BaseLifeCycle
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView
import ru.techmas.barrier.utils.Injector
import ru.techmas.barrier.utils.KeyboardHelper
import ru.techmas.barrier.utils.Navigator

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

@Suppress("UNCHECKED_CAST")
abstract class BaseFragment : MvpAppCompatFragment(), NavigatorActivityView, BaseLifeCycle {

    protected val TAG = javaClass.simpleName
    protected var rootView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.viewComponent?.inject(this)
    }

    override fun close() {
        activity?.finish()
    }

    protected fun hideKeyboard() {
        activity?.let { KeyboardHelper.hide(it) }
    }

    override fun startActivity(activityClass: Class<out BaseActivity>) {
        activity?.let { Navigator.startActivity(it, activityClass, false) }
    }

    override fun startActivityForResult(activityClass: Class<out BaseActivity>, requestCode: Int) {
        activity?.let { Navigator.startActivityForResult(it, activityClass, requestCode) }
    }

    override fun startFragment(baseFragment: BaseFragment, addToBackStack: Boolean) {
        activity?.let { Navigator.startFragment(baseFragment, it.supportFragmentManager, R.id.ltContainer, addToBackStack) }
    }

    override fun startFragment(baseFragment: BaseFragment) {
        activity?.let { Navigator.startFragment(baseFragment, it.supportFragmentManager, R.id.ltContainer) }
    }

    protected fun hideView(view: View) {
        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
        }
    }

    protected fun showView(view: View) {
        if (view.visibility == View.GONE) {
            view.visibility = View.VISIBLE
        }
    }

//    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
//        if (Navigator.isFragmentAnimationDisabled) {
//            val a = object : Animation() {}
//            a.duration = 0
//            return a
//        }
//        return super.onCreateAnimation(transit, enter, nextAnim)
//    }

//    protected fun <T : View> bindView(@IdRes id: Int): T {
//        return rootView!!.findViewById(id)
//    }

    protected fun bindDimen(@DimenRes id: Int): Int {
        return resources.getDimension(id).toInt()
    }

    protected fun bindColor(@ColorRes id: Int): Int {
        return ContextCompat.getColor(context!!, id)
    }

    protected fun bindString(@StringRes id: Int): String {
        return getString(id)
    }

}
