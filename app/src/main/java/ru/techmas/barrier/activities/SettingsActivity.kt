package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.SettingsView
import ru.techmas.barrier.presenters.SettingsPresenter
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.utils.Injector


class SettingsActivity : BaseSingleActivity(), SettingsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
    }

    override fun setupUI() {

    }

    override fun setupUX() {

    }

    @InjectPresenter
    lateinit var settingsPresenter: SettingsPresenter

    @ProvidePresenter
    internal fun provideSettingsPresenter() = Injector.presenterComponent!!.settingsPresenter

    companion object {

        private const val LAYOUT = R.layout.activity_settings

        fun getIntent(context: Context): Intent {
            return Intent(context, SettingsActivity::class.java)
        }
    }
}
