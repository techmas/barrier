package ru.techmas.barrier.interfaces.views

import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView

interface AuthView : BaseView, NavigatorActivityView {
    fun showCode()
}
