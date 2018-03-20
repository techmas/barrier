package ru.techmas.barrier.presenters

import android.support.design.widget.NavigationView
import android.util.Log
import android.view.MenuItem
import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.R
import ru.techmas.barrier.activities.Test2Activity
import ru.techmas.barrier.activities.TestActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.interfaces.views.MainView
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject
internal constructor(restApi: RestApi) : BasePresenter<MainView>(), NavigationView.OnNavigationItemSelectedListener {

    init {
        this.restApi = restApi
        Log.d(TAG, "MainActivityPresenter: text")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_settings -> viewState.startActivity(TestActivity::class.java)
        }
        viewState.closeDrawers()
        return false
    }
}
