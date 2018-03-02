package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.AuthView
import ru.techmas.barrier.presenters.AuthPresenter
import ru.techmas.barrier.activities.BaseActivity
import ru.techmas.barrier.R

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.App
import ru.techmas.barrier.utils.Injector


class AuthActivity : BaseActivity(), AuthView {

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    internal fun provideAuthPresenter(): AuthPresenter {
        return  Injector.presenterComponent!!.authPresenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
    }

    override fun setupUI() {
    }

    override fun setupUX() {
    }

    companion object {

        private val LAYOUT = R.layout.activity_auth

        fun getIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }
}
