package ru.techmas.barrier.activities


import android.accounts.Account
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*

import ru.techmas.barrier.R
import ru.techmas.barrier.interfaces.views.MainView
import ru.techmas.barrier.presenters.MainActivityPresenter
import ru.techmas.barrier.utils.Injector

class MainActivity : BaseActivity(), MainView {

    override fun setupHeader(account: Account) {

    }

    lateinit var toggle: ActionBarDrawerToggle
    var hasBackButton: Boolean = false

    companion object {
        private val LAYOUT = R.layout.activity_main
    }

    @InjectPresenter
    lateinit var mainPresenter: MainActivityPresenter

    @ProvidePresenter
    internal fun provideMainActivityPresenter(): MainActivityPresenter {
        return Injector.presenterComponent!!.mainActivityPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun setupUI() {

    }

    override fun setupUX() {
//        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        setDrawerState(true)
        navigationView.setNavigationItemSelectedListener(mainPresenter)
    }

    fun setDrawerState(isEnabled: Boolean) {
        if (isEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            toggle.setDrawerIndicatorEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            hasBackButton = false
            toggle.syncState()
        } else {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            hasBackButton = true
            toggle.setDrawerIndicatorEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            toggle.syncState()
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawers()
        else
            super.onBackPressed()
    }

     override fun closeDrawers() {
        drawer.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> if (!hasBackButton) {
                hideKeyboard()
                drawer.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
