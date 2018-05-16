package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.SettingsView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.activities.AuthActivity
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class SettingsPresenter @Inject
constructor(
        private val preferenceHelper: PreferenceHelper) : BasePresenter<SettingsView>() {

    init {
        viewState.setHand(preferenceHelper.hand!!)
    }

    fun logout() {
        preferenceHelper.exit()
        viewState.close()
        viewState.startActivity(AuthActivity::class.java)
    }

    fun showDemo() {
        viewState.showStub()
    }

    fun toggleHand(checked: Boolean) {
        preferenceHelper.hand = checked
    }

}