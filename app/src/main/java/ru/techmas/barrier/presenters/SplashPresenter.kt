package ru.techmas.barrier.presenters

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.activities.AuthActivity

import javax.inject.Inject

import ru.techmas.barrier.activities.MainActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.interfaces.views.SplashView
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.presenter.PreferenceHelper


@InjectViewState
class SplashPresenter @Inject
internal constructor(
        private val restApi: RestApi,
        private val preferenceHelper: PreferenceHelper,
        private val appData: AppData)
    : BasePresenter<SplashView>() {

    init {
        appData.photos = preferenceHelper.getPhotos()
    }

    fun startNext() {
        viewState.showErrorConnection(false)
        if (preferenceHelper.isFirstRun) {
            viewState.startActivity(AuthActivity::class.java)
            viewState.close()
        } else {
            viewState.startActivity(MainActivity::class.java)
            viewState.close()
        }
    }
}
