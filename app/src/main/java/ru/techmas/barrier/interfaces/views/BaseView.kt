package ru.techmas.barrier.interfaces.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by Alex Bykov on 25.04.2017.
 * You can contact me at: me@alexbykov.ru.
 */

@StateStrategyType(SkipStrategy::class)
interface BaseView : MvpView
