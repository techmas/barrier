package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.SupportView

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject


@InjectViewState
class SupportPresenter @Inject
constructor() : BasePresenter<SupportView>()