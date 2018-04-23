package ru.techmas.barrier.presenters

import android.util.Log
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by Alex Bykov on 15.02.2017.
 * You can contact me at: me@alexbykov.ru.
 */

open class BasePresenter<View : MvpView> : MvpPresenter<View>() {

    open val TAG = javaClass.simpleName
    private val compositeDisposable = CompositeDisposable()

    protected fun unSubscribeOnDestroy(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    open fun handleError(throwable: Throwable?) {
        Log.d(TAG, "Error" + throwable.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
