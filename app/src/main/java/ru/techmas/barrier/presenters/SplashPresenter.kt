package ru.techmas.barrier.presenters

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject

import ru.techmas.barrier.activities.MainActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.interfaces.views.SplashView
import ru.techmas.barrier.utils.presenter.TokenHelper


@InjectViewState
class SplashPresenter @Inject
internal constructor(restApi: RestApi, preferenceHelper: TokenHelper) : BasePresenter<SplashView>() {

    init {
        this.restApi = restApi
        this.tokenHelper = preferenceHelper
        startNext()
    }


    fun startNext() {
        viewState.showErrorConnection(false)
        if (tokenHelper!!.isFirstRun) {
            viewState.startActivity(MainActivity::class.java)
        }
    }

}
