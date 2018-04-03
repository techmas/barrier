package ru.techmas.barrier.presenters

import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
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
        viewState.startFragment(MainFragment.newInstance())
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
//            R.id.action_add -> viewState.startActivity(AddBarrierActivity::class.java)
        }
    }
}
