package ru.techmas.barrier.presenters

import ru.techmas.barrier.interfaces.views.AuthView

import com.arellomobile.mvp.InjectViewState
import retrofit2.Response
import ru.techmas.barrier.activities.SupportActivity
import ru.techmas.barrier.api.ApiResponse
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.utils.RxUtils

import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject
constructor(private val restApi: RestApi) : BasePresenter<AuthView>() {

    fun sendSms(number: String) {
        val request = restApi.user.sendSms(number)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successSendSms(it)}, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successSendSms(response: Response<ApiResponse<String>>?) {
        viewState.showCode()
    }

    fun supportClick() {
        viewState.startActivity(SupportActivity::class.java)
    }
}