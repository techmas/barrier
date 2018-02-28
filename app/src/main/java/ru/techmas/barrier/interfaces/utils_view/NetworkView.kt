package ru.techmas.barrier.interfaces.utils_view

import ru.techmas.barrier.interfaces.views.BaseView

/**
 * Date: 04.06.2017
 * Time: 12:02
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
interface NetworkView : BaseView {
    fun showProgress(show: Boolean)
    fun showData(show: Boolean)
    fun showError(show: Boolean, text: String)
}
