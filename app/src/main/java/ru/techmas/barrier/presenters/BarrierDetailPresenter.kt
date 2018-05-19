package ru.techmas.barrier.presenters


import android.graphics.Bitmap
import ru.techmas.barrier.interfaces.views.BarrierDetailView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.CameraHelper
import ru.techmas.barrier.utils.GalleryHelper
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class BarrierDetailPresenter @Inject
constructor(private val restApi: RestApi,
            private val preferenceHelper: PreferenceHelper,
            private val appData: AppData) : BasePresenter<BarrierDetailView>(), CameraHelper.OnSuccessListener, CameraHelper.OnErrorListener, GalleryHelper.OnSuccessListener, GalleryHelper.OnErrorListener {

    private var fileName: String = ""

    init {
        viewState.showData(appData.barrier, appData.photos)
    }

    override fun errorCamera(message: String) {
        viewState.showError(message)
    }

    override fun successCameraPhoto(name: String, bitmap: Bitmap) {
        fileName = name
        viewState.showPhoto(bitmap)
    }

    override fun errorGallery(message: String) {
        viewState.showError(message)
    }

    override fun successGalleryPhoto(name: String, bitmap: Bitmap) {
        fileName = name
        viewState.showPhoto(bitmap)
    }


    fun removeBarrier() {
        val request = restApi.barrier.removeBarrier(preferenceHelper.number!!, preferenceHelper.token!!, appData.barrier.id)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successRemoveBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successRemoveBarrier(response: StateResponse) {
        appData.barriers.remove(appData.barrier)
        appData.photos.remove(appData.barrier.number)
        appData.added.remove(appData.barrier.number)
        viewState.close()
    }

    internal fun updateBarrier(name: String, address: String) {
        val request = restApi.barrier.updateBarrier(
                preferenceHelper.number!!,
                preferenceHelper.token!!, "", appData.barrier.id, name, 0.0,0.0, address, name)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successUpdateBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    private fun successUpdateBarrier(response: StateResponse) {
        if (fileName.isNotEmpty())
            appData.photos[appData.barrier.number] = fileName
        preferenceHelper.savePhotos(appData.photos)
        viewState.close()
    }

}