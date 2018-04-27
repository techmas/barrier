package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.SupportView
import ru.techmas.barrier.presenters.SupportPresenter
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.utils.Injector


class SupportActivity : BaseSingleActivity(), SupportView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
    }

    override fun setupUI() {
    }

    override fun setupUX() {
    }

    @InjectPresenter
    lateinit var supportPresenter: SupportPresenter

    @ProvidePresenter
    internal fun provideSupportPresenter() = Injector.presenterComponent!!.supportPresenter

    companion object {

        private val LAYOUT = R.layout.activity_support

        fun getIntent(context: Context): Intent {
            return Intent(context, SupportActivity::class.java)
        }
    }
}
