package ru.techmas.barrier.interfaces.views

import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView
import ru.techmas.barrier.models.Photos


interface BarrierDetailView : BaseView, NavigatorActivityView {
    fun showData(barrier: Barrier, photos: Photos)
}
