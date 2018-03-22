package ru.techmas.barrier.activities

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View

import com.arellomobile.mvp.MvpAppCompatActivity

import javax.inject.Inject

import ru.techmas.barrier.R
import ru.techmas.barrier.interfaces.utils_view.BaseLifeCycle
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView
import ru.techmas.barrier.utils.AnimationHelper
import ru.techmas.barrier.utils.Injector
import ru.techmas.barrier.utils.KeyboardHelper
import ru.techmas.barrier.utils.Navigator

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

abstract class BaseSingleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
