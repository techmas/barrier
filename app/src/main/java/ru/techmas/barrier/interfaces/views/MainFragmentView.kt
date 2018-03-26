package ru.techmas.barrier.interfaces.views


import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView

interface MainFragmentView : BaseView, NavigatorActivityView {

    fun showData(barriers: Barriers)
}
