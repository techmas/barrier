package ru.techmas.barrier.di.modules

import dagger.Module
import dagger.Provides
import ru.techmas.barrier.App
import ru.techmas.barrier.utils.presenter.Resources

/**
 * Date: 04.06.2017
 * Time: 11:06
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */

@Module
class UtilsModule(private val app: App) {

    @Provides
    internal fun provideResources(): Resources {
        return Resources(app)
    }

}
