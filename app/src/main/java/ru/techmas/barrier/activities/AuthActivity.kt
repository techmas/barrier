package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.AuthView
import ru.techmas.barrier.presenters.AuthPresenter
import ru.techmas.barrier.R

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_auth.*
import ru.techmas.barrier.utils.Injector


class AuthActivity : BaseActivity(), AuthView {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun showCode() {
        hideView(btnGetSmsCode)
        showView(llSmsCode)
    }

    override fun setupUI() {
    }

    override fun setupUX() {
        btnGetSmsCode.setOnClickListener { authPresenter.sendSms(etPhone.text.toString()) }
        ivSupport.setOnClickListener { authPresenter.supportClick() }
        etSmsCode.setOnFocusChangeListener { v, hasFocus -> authPresenter.checkCode(etSmsCode.text.toString(), hasFocus)}
    }

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    internal fun provideAuthPresenter() = Injector.presenterComponent!!.authPresenter

    companion object {
        private const val LAYOUT = R.layout.activity_auth

        fun getIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }
}
