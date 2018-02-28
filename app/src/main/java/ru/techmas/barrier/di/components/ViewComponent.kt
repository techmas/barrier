package ru.techmas.barrier.di.components

import javax.inject.Singleton

import dagger.Component
import ru.techmas.barrier.App
import ru.techmas.barrier.activities.BaseActivity
import ru.techmas.barrier.di.modules.ViewModule
import ru.techmas.barrier.fragments.BaseFragment

/**
 * Date: 04.06.2017
 * Time: 10:30
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */


@Singleton
@Component(modules = arrayOf(ViewModule::class))
interface ViewComponent {
    fun inject(app: App)
    fun inject(baseFragment: BaseFragment)
    fun inject(baseActivity: BaseActivity)
}
