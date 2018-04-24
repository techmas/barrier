package ru.techmas.barrier.presenters


import android.view.MenuItem
import ru.techmas.barrier.interfaces.views.AddBarrierView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.Const
import ru.techmas.barrier.R
import ru.techmas.barrier.activities.AddBarrierActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class AddBarrierPresenter @Inject
constructor(private val restApi: RestApi, val preferenceHelper: PreferenceHelper, val appData: AppData) : BasePresenter<AddBarrierView>() {

    fun addBarrier(name: String, address: String, phone: String) {
        val request = restApi.barrier.addBarrier(
                preferenceHelper.number!!,
                preferenceHelper.token!!, "", phone, address, name)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successAddBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successAddBarrier(response: StateResponse) {
        viewState.close()
    }

    override fun handleError(throwable: Throwable?) {
        super.handleError(throwable)
        viewState.close()
    }
}