package ru.techmas.barrier.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import ru.techmas.barrier.interfaces.views.AddBarrierView
import ru.techmas.barrier.presenters.AddBarrierPresenter
import ru.techmas.barrier.R


import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_add_barrier.*
import ru.techmas.barrier.utils.CameraHelper
import ru.techmas.barrier.utils.GalleryHelper

import ru.techmas.barrier.utils.Injector
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build


class AddBarrierActivity : BaseSingleActivity(), AddBarrierView {

    private var cameraHelper: CameraHelper? = null
    private var galleryHelper: GalleryHelper? = null

    // TODO: Refactor to separate manager
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this, R.style.MyAlertDialogTheme)
        pictureDialog.setTitle(getString(R.string.choose_action))
        val pictureDialogItems = arrayOf(getString(R.string.gallery_pick), getString(R.string.camera_pick))
        pictureDialog.setItems(pictureDialogItems, { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> getCameraPhoto()
            }
        })
        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        galleryHelper = GalleryHelper(this)
                .setFilePrefix("Barrier")
                .setViewDimensions(ivPhoto)
                .onSuccess(addBarrierPresenter)
                .onError(addBarrierPresenter)
                .execute()
    }


    private fun getCameraPhoto() {
        cameraHelper = CameraHelper(this)
                .setFilePrefix("Barrier")
                .setViewDimensions(ivPhoto)
                .onSuccess(addBarrierPresenter)
                .onError(addBarrierPresenter)
                .execute()
    }

    override fun showPhoto(bitmap: Bitmap) {
        ivPhoto.setImageBitmap(bitmap)
    }

    override fun close() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun setupUI() {
        etName.clearFocus()
    }

    override fun setupUX() {
        ivPhoto.setOnClickListener { showPictureDialog() }
        btnAdd.setOnClickListener { addBarrierPresenter.addBarrier(
                etName.text.toString(),
                etAddress.text.toString(),
                etPhone.text.toString()) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        galleryHelper?.onActivityResult(requestCode, resultCode, data)
        cameraHelper?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    @InjectPresenter
    lateinit var addBarrierPresenter: AddBarrierPresenter

    @ProvidePresenter
    internal fun provideAddBarrierPresenter() = Injector.presenterComponent!!.addBarrierPresenter

    companion object {

        private val LAYOUT = R.layout.activity_add_barrier

        fun getIntent(context: Context): Intent {
            return Intent(context, AddBarrierActivity::class.java)
        }
    }
}
