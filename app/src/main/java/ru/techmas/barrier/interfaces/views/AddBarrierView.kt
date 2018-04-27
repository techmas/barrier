package ru.techmas.barrier.interfaces.views


import android.graphics.Bitmap

import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView

interface AddBarrierView : BaseView, NavigatorActivityView {
    fun showPhoto(bitmap: Bitmap)
}
