package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.AddBarrierView
import ru.techmas.barrier.presenters.AddBarrierPresenter
import ru.techmas.barrier.activities.BaseActivity
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.App
import ru.techmas.barrier.utils.Injector


class AddBarrierActivity : BaseActivity(), AddBarrierView {
    override fun setupUI() {

    }

    override fun setupUX() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    @InjectPresenter
    lateinit var addBarrierPresenter: AddBarrierPresenter

    @ProvidePresenter
    internal fun provideAddBarrierPresenter() = Injector.presenterComponent!!.addBarrierPresenter

    companion object {

        private val LAYOUT = R.layout.activity_add_barrier

        fun getIntent(context: Context): Intent {
            return Intent(context, AddBarrierActivity::class.java)
        }
    }
}
