package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.SettingsView

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject


@InjectViewState
class SettingsPresenter @Inject
constructor() : BasePresenter<SettingsView>()