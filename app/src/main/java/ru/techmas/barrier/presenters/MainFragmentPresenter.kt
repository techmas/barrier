package ru.techmas.barrier.presenters


import android.app.Activity
import android.content.Intent
import ru.techmas.barrier.interfaces.views.MainFragmentView
import ru.techmas.barrier.interfaces.views.MainView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.Const
import ru.techmas.barrier.activities.BarrierDetailActivity
import ru.techmas.barrier.adapters.BarriersAdapter
import ru.techmas.barrier.adapters.BaseRecyclerAdapter
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class MainFragmentPresenter @Inject
internal constructor(
        private val restApi: RestApi,
        private val preferenceHelper: PreferenceHelper) :
        BasePresenter<MainFragmentView>(), BarriersAdapter.OnBarrierClickListener {

    override fun onClickOpen(item: Barrier) {
        val request = restApi.barrier.openBarrier(preferenceHelper.number!!, preferenceHelper.token!!, "open", item.id)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successOpenBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successOpenBarrier(response: StateResponse) {
//        if (response.state == Const.State.OK)
//            viewState.selectOpen
    }

    override fun onClickSettings(item: Barrier) {
        viewState.startActivity(BarrierDetailActivity::class.java)
    }

    override fun onClickCamera(item: Barrier) {
    }

    init {
        getBarriers()
    }

    fun getBarriers() {
        val request = restApi.barrier.getBarriers(preferenceHelper.number!!, preferenceHelper.token!!, "")
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successGetData(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successGetData(response: Barriers) {
        viewState.showData(response)
    }

//    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (resultCode == Activity.RESULT_OK)
//            when (requestCode) {
//                Const.ActivityRequest.ADD_BARRIER -> getBarriers()
//            }
//    }
}
