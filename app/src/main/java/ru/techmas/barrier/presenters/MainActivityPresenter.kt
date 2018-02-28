package ru.techmas.barrier.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.interfaces.views.MainView
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter @Inject
internal constructor(restApi: RestApi) : BasePresenter<MainView>() {


    init {
        this.restApi = restApi
        Log.d(TAG, "MainActivityPresenter: text")
    }

}
