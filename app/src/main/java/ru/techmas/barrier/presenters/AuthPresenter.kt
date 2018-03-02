package ru.techmas.barrier.presenters

import ru.techmas.barrier.interfaces.views.AuthView

import com.arellomobile.mvp.InjectViewState

import javax.inject.Inject


@InjectViewState
class AuthPresenter @Inject
constructor() : BasePresenter<AuthView>()