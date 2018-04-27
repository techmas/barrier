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
constructor(private val restApi: RestApi,
            private val preferenceHelper: PreferenceHelper,
            private val appData: AppData) : BasePresenter<BarrierDetailView>() {

    init {
        viewState.showData(appData.barrier, appData.photos)
    }

    fun removeBarrier() {
        val request = restApi.barrier.removeBarrier(preferenceHelper.number!!, preferenceHelper.token!!, appData.barrier.id)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successRemoveBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successRemoveBarrier(response: StateResponse) {
//        appData.barriers.remove(appData.barrier)
        viewState.close()
    }

    internal fun updateBarrier(name: String, address: String) {
        val request = restApi.barrier.updateBarrier(
                preferenceHelper.number!!,
                preferenceHelper.token!!, "", appData.barrier.id, name, 0.0,0.0, address, name)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successUpdateBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successUpdateBarrier(response: StateResponse) {
        viewState.close()
    }

}