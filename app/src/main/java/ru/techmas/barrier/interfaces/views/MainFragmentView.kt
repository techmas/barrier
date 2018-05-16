package ru.techmas.barrier.interfaces.views


import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.Barriers
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView
import ru.techmas.barrier.models.Photos

interface MainFragmentView : BaseView, NavigatorActivityView {

    fun showData(barriers: Barriers, photos: Photos, hand: Boolean)
    fun updateData(barrier: Barrier)
    fun showCamera(item: Barrier)
}
