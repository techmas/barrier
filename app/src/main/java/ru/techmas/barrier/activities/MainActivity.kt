package ru.techmas.barrier.activities


import android.accounts.Account
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tabs_toolbar.*

import ru.techmas.barrier.R
import ru.techmas.barrier.interfaces.views.MainView
import ru.techmas.barrier.presenters.MainActivityPresenter
import ru.techmas.barrier.utils.Injector

class MainActivity : BaseActivity(), MainView {

    lateinit var toggle: ActionBarDrawerToggle
    var hasBackButton: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun setupHeader(account: Account) {

    }

    override fun setupUI() {
        toolbar.setNavigationIcon(R.drawable.ic_menu_24dp)
        setSupportActionBar(toolbar)
        title = getString(R.string.title_main)
//
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//        supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_menu_24dp))
    }

    override fun setupUX() {
        toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle.syncState()
        drawer.addDrawerListener(toggle)
        setDrawerState(true)
        navigationView.setNavigationItemSelectedListener(mainPresenter)
    }

    private fun setDrawerState(isEnabled: Boolean) {
        if (isEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            toggle.isDrawerIndicatorEnabled = true
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            hasBackButton = false
            toggle.syncState()
        } else {
            supportActionBar?.setHomeButtonEnabled(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            hasBackButton = true
            toggle.isDrawerIndicatorEnabled = false
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.barrier_toolbar_menu, menu)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mainPresenter.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> if (!hasBackButton) {
                hideKeyboard()
                drawer.openDrawer(GravityCompat.START)
            }
        }
        mainPresenter.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun showStub() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.alert_message))
                .setPositiveButton("OK") {dialog, which -> dialog.dismiss()}
                .create().show()
    }

    @InjectPresenter
    lateinit var mainPresenter: MainActivityPresenter

    @ProvidePresenter
    internal fun provideMainActivityPresenter() = Injector.presenterComponent!!.mainActivityPresenter

    companion object {
        private const val LAYOUT = R.layout.activity_main
    }
}
