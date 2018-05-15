package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.SettingsView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.activities.AuthActivity
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class SettingsPresenter @Inject
constructor(val preferenceHelper: PreferenceHelper) : BasePresenter<SettingsView>() {

    fun logout() {
        preferenceHelper.exit()
        viewState.close()
        viewState.startActivity(AuthActivity::class.java)
    }

    fun showDemo() {
        viewState.showStub()
    }

}