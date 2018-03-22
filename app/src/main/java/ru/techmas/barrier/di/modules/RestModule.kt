package ru.techmas.barrier.di.modules

import android.preference.PreferenceManager

import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import ru.techmas.barrier.App
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.utils.presenter.PreferenceHelper

/**
 * Date: 04.06.2017
 * Time: 10:46
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */

@Module
class RestModule(app: App) {

    private val preferenceHelper: PreferenceHelper = PreferenceHelper(PreferenceManager.getDefaultSharedPreferences(app))
    private val restApi: RestApi = RestApi()

    @Singleton
    @Provides
    internal fun provideRestApi() = restApi

    @Singleton
    @Provides
    internal fun providePreferenceHelper() = preferenceHelper

}

