package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.BarrierDetailView
import ru.techmas.barrier.presenters.BarrierDetailPresenter
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_barrier_detail.*

import ru.techmas.barrier.utils.Injector


class BarrierDetailActivity : BaseSingleActivity(), BarrierDetailView {

    override fun setupUI() {

    }

    override fun setupUX() {
        btnDelete.setOnClickListener { barrierDetailPresenter.removeBarrier() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    @InjectPresenter
    lateinit var barrierDetailPresenter: BarrierDetailPresenter

    @ProvidePresenter
    internal fun provideBarrierDetailPresenter() = Injector.presenterComponent!!.barrierDetailPresenter

    companion object {

        private val LAYOUT = R.layout.activity_barrier_detail

        fun getIntent(context: Context): Intent {
            return Intent(context, BarrierDetailActivity::class.java)
        }
    }
}
