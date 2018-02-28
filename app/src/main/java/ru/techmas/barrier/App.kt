package ru.techmas.barrier


import android.app.Application
import ru.techmas.barrier.di.components.DaggerPresenterComponent
import ru.techmas.barrier.di.components.DaggerViewComponent
import ru.techmas.barrier.di.modules.RestModule
import ru.techmas.barrier.di.modules.UtilsModule
import ru.techmas.barrier.di.modules.ViewModule
import ru.techmas.barrier.utils.Injector
import timber.log.Timber


/**
 * Created by Alex Bykov on 09.11.2016.
 * You can contact me at: me@alexbykov.ru.
 */

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        setupDagger2()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDagger2() {
        val presenterComponent = DaggerPresenterComponent.builder()
                .restModule(RestModule(this))
                .utilsModule(UtilsModule(this))
                .build()
        presenterComponent.inject(this)

        val viewComponent = DaggerViewComponent.builder()
                .viewModule(ViewModule(this))
                .build()
        viewComponent.inject(this)

        Injector.presenterComponent = presenterComponent
        Injector.viewComponent = viewComponent
    }


}
