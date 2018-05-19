package ru.techmas.barrier.presenters


import android.graphics.Bitmap
import android.view.MenuItem
import ru.techmas.barrier.interfaces.views.AddBarrierView

import com.arellomobile.mvp.InjectViewState
import ru.techmas.barrier.Const
import ru.techmas.barrier.R
import ru.techmas.barrier.activities.AddBarrierActivity
import ru.techmas.barrier.api.RestApi
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.api.models.StateResponse
import ru.techmas.barrier.models.AppData
import ru.techmas.barrier.utils.CameraHelper
import ru.techmas.barrier.utils.GalleryHelper
import ru.techmas.barrier.utils.RxUtils
import ru.techmas.barrier.utils.presenter.PreferenceHelper

import javax.inject.Inject


@InjectViewState
class AddBarrierPresenter @Inject
constructor(
        private val restApi: RestApi,
        private val preferenceHelper: PreferenceHelper,
        private val appData: AppData)
    : BasePresenter<AddBarrierView>(), CameraHelper.OnSuccessListener, CameraHelper.OnErrorListener, GalleryHelper.OnSuccessListener, GalleryHelper.OnErrorListener {
    private lateinit var phone: String
    private var fileName: String = ""

    override fun errorGallery(message: String) {
        viewState.showError(message)
    }

    override fun successGalleryPhoto(name: String, bitmap: Bitmap) {
        fileName = name
        viewState.showPhoto(bitmap)
    }

    override fun errorCamera(message: String) {
        viewState.showError(message)
    }

    override fun successCameraPhoto(name: String, bitmap: Bitmap) {
        fileName = name
        viewState.showPhoto(bitmap)
    }

    fun addBarrier(name: String, address: String, phone: String) {
        this.phone = formatPhone(phone)
        val request = restApi.barrier.addBarrier(
                preferenceHelper.number!!,
                preferenceHelper.token!!, "", this.phone, address, name)
                .compose(RxUtils.httpSchedulers())
                .subscribe({ successAddBarrier(it) }, { handleError(it) })
        unSubscribeOnDestroy(request)
    }

    // TODO: Refactor this
    private fun formatPhone(phone: String):String {
        return Regex("[^0-9]").replace(phone, "")
    }

    private fun successAddBarrier(response: StateResponse) {
        if (fileName.isNotEmpty())
            appData.photos[phone] = fileName
        appData.added.add(phone)
        preferenceHelper.savePhotos(appData.photos)
        viewState.close()
    }

    override fun handleError(throwable: Throwable) {
        super.handleError(throwable)
        viewState.showError(throwable.localizedMessage)
//        viewState.close()
    }

}