package ru.techmas.barrier.presenters

import android.app.Activity
import android.content.Intent
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.Const
import ru.techmas.barrier.R
import ru.techmas.barrier.activities.AddBarrierActivity
import ru.techmas.barrier.activities.SettingsActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.fragments.MainFragment
import ru.techmas.barrier.interfaces.views.MainView
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject
internal constructor(val restApi: RestApi) : BasePresenter<MainView>(), NavigationView.OnNavigationItemSelectedListener {

    init {
        viewState.startFragment(MainFragment.newInstance(), false)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_settings -> viewState.startActivity(SettingsActivity::class.java)
        }
        viewState.closeDrawers()
        return false
    }

    fun onOptionsItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.action_add -> viewState.startActivityForResult(AddBarrierActivity::class.java, Const.ActivityRequest.ADD_BARRIER)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK)
            when (requestCode) {
                Const.ActivityRequest.ADD_BARRIER -> viewState.startFragment(MainFragment.newInstance())
            }
    }

}
