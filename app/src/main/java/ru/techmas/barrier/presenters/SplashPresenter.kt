package ru.techmas.barrier.presenters

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.activities.AuthActivity

import javax.inject.Inject

import ru.techmas.barrier.activities.MainActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.interfaces.views.SplashView
import ru.techmas.barrier.utils.presenter.PreferenceHelper


@InjectViewState
class SplashPresenter @Inject
internal constructor(val restApi: RestApi, val preferenceHelper: PreferenceHelper) : BasePresenter<SplashView>() {

    init {
        startNext()
    }

    fun startNext() {
        viewState.showErrorConnection(false)
        if (preferenceHelper.isFirstRun)
            viewState.startActivity(AuthActivity::class.java)
        else
            viewState.startActivity(MainActivity::class.java)
    }
}
