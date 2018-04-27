package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.MainFragmentView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.Const
import ru.techmas.barrier.activities.BarrierDetailActivity
import ru.techmas.barrier.adapters.BarriersAdapter
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class MainFragmentPresenter @Inject
internal constructor(
        private val restApi: RestApi,
        private val appData: AppData,
        private val preferenceHelper: PreferenceHelper) :
        BasePresenter<MainFragmentView>(), BarriersAdapter.OnBarrierClickListener {

    init {
//        getBarriers()
    }

    override fun onClickOpen(item: Barrier) {
        appData.barrier = item
        val request = restApi.barrier.openBarrier(preferenceHelper.number!!, preferenceHelper.token!!, "open", item.id)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successOpenBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successOpenBarrier(response: StateResponse) {
        appData.barrier.opened = true
        if (response.state == Const.State.OK)
            viewState.updateData(appData.barrier)
//            viewState.selectOpen(appData.barrier)
    }

    override fun onClickSettings(item: Barrier) {
        appData.barrier = item
        viewState.startActivity(BarrierDetailActivity::class.java)
    }

    override fun onClickCamera(item: Barrier) {
        viewState.showCamera(item)
    }

    fun getBarriers() {
        val request = restApi.barrier.getBarriers(preferenceHelper.number!!, preferenceHelper.token!!, "")
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successGetData(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        viewState.showError(throwable.localizedMessage)
    }

    private fun successGetData(response: Barriers) {
        appData.barriers = response
        viewState.showData(response, appData.photos)
    }

//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK)
//            when (requestCode) {
//                Const.ActivityRequest.ADD_BARRIER -> getBarriers()
//            }
//    }
}
