package ru.techmas.barrier.presenters


import ru.techmas.barrier.interfaces.views.BarrierDetailView

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject


@InjectViewState
class BarrierDetailPresenter @Inject
constructor() : BasePresenter<BarrierDetailView>()