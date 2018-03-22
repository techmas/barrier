package ru.techmas.barrier.presenters

import ru.techmas.barrier.interfaces.views.AuthView

import com.arellomobile.mvp.InjectViewState
import retrofit2.Response
import ru.techmas.barrier.activities.MainActivity
import ru.techmas.barrier.activities.SupportActivity
import ru.techmas.barrier.api.ApiResponse
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.TokenResponse
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject
constructor(private val restApi: RestApi, val preferenceHelper: PreferenceHelper) : BasePresenter<AuthView>() {

    fun sendSms(number: String) {
        preferenceHelper.number = number
        val request = restApi.user.sendSms(number)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successSendSms(it)}, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successSendSms(response: TokenResponse) {
        viewState.showCode()
    }

    fun supportClick() {
        viewState.startActivity(SupportActivity::class.java)
    }

    fun checkCode(code: String, hasFocus: Boolean) {
        if (!hasFocus) {
            val request = restApi.user.checkCode(preferenceHelper.number!!, code)
                    .compose(RxUtils.httpSchedulers())
                    .subscribe({ successCodeCheck(it) }, { handleError(it) })
            unSubscribeOnDestroy(request)
        }
    }

    private fun successCodeCheck(response: TokenResponse) {
        response.key?.let {
            preferenceHelper.token = it
            viewState.startActivity(MainActivity::class.java)
        }
    }
}