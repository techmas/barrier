package ru.techmas.barrier.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.SettingsView
import ru.techmas.barrier.presenters.SettingsPresenter
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_settings.*

import ru.techmas.barrier.utils.Injector


class SettingsActivity : BaseSingleActivity(), SettingsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun setupUI() {
    }

    override fun setupUX() {
        btnLogout.setOnClickListener { settingsPresenter.logout() }
        tvSupport.setOnClickListener { startActivity(JivoActivity::class.java) }
        tvDemo.setOnClickListener { settingsPresenter.showDemo() }
    }

    override fun showStub() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.alert_title))
                .setMessage(getString(R.string.alert_message))
                .setPositiveButton("OK") {dialog, which -> dialog.dismiss()}
                .create().show()
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
