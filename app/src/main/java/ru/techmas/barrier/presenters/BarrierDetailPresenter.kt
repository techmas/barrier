package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.BarrierDetailView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class BarrierDetailPresenter @Inject
constructor(val restApi: RestApi, val preferenceHelper: PreferenceHelper, val appData: AppData) : BasePresenter<BarrierDetailView>() {

    fun removeBarrier() {
        val request = restApi.barrier.removeBarrier(preferenceHelper.number!!, preferenceHelper.token!!, appData.barrier.id)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successRemoveBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)

    }

    private fun successRemoveBarrier(response: StateResponse) {
        appData.barriers.remove(appData.barrier)
        viewState.close()
    }
}