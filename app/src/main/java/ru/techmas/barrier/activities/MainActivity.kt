package ru.techmas.barrier.activities


import android.os.Bundle

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.R
import ru.techmas.barrier.interfaces.views.MainView
import ru.techmas.barrier.presenters.MainActivityPresenter
import ru.techmas.barrier.utils.Injector

class MainActivity : BaseActivity(), MainView {


    companion object {
        private val LAYOUT = R.layout.activity_main
    }

    @InjectPresenter
    lateinit var mainPresenter: MainActivityPresenter

    @ProvidePresenter
    internal fun provideMainActivityPresenter(): MainActivityPresenter {
        return Injector.presenterComponent!!.mainActivityPresenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
    }

    override fun setupUI() {

    }

    override fun setupUX() {

    }


}
