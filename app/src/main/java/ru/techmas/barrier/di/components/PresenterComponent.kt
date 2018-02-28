package ru.techmas.barrier.di.components

import javax.inject.Singleton

import dagger.Component
import ru.techmas.barrier.App
import ru.techmas.barrier.di.modules.RestModule
import ru.techmas.barrier.di.modules.UtilsModule
import ru.techmas.barrier.presenters.MainActivityPresenter
import ru.techmas.barrier.presenters.SplashPresenter

/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */


@Singleton
@Component(modules = arrayOf(RestModule::class, UtilsModule::class))
interface PresenterComponent {
    //@formatter:off
    fun inject(app: App)

    val mainActivityPresenter: MainActivityPresenter
    val splashPresenter: SplashPresenter
}
