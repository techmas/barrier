package ru.techmas.barrier.interfaces.views

import android.accounts.Account
import ru.techmas.barrier.fragments.BaseFragment
import ru.techmas.barrier.interfaces.utils_view.NavigatorActivityView

interface MainView : BaseView, NavigatorActivityView {
    fun closeDrawers()
    fun setupHeader(account: Account)
    fun showStub()
}


