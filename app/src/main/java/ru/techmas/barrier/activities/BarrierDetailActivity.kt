package ru.techmas.barrier.activities

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import ru.techmas.barrier.interfaces.views.BarrierDetailView
import ru.techmas.barrier.presenters.BarrierDetailPresenter
import ru.techmas.barrier.R

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_barrier_detail.*
import ru.techmas.barrier.api.models.Barrier
import ru.techmas.barrier.models.Photos
import ru.techmas.barrier.utils.CameraHelper
import ru.techmas.barrier.utils.ImageLoader

import ru.techmas.barrier.utils.Injector
import android.provider.MediaStore
import android.view.View
import ru.techmas.barrier.utils.GalleryHelper
import java.io.IOException


class BarrierDetailActivity : BaseSingleActivity(), BarrierDetailView {

    private var cameraHelper: CameraHelper? = null
    private var galleryHelper: GalleryHelper? = null

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
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
                .onSuccess(barrierDetailPresenter)
                .onError(barrierDetailPresenter)
                .execute()
    }

    private fun getCameraPhoto() {
        cameraHelper = CameraHelper(this)
                .setFilePrefix("Barrier")
                .setViewDimensions(ivPhoto)
                .onSuccess(barrierDetailPresenter)
                .onError(barrierDetailPresenter)
                .execute()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        galleryHelper?.onActivityResult(requestCode, resultCode, data)
        cameraHelper?.onActivityResult(requestCode, resultCode, data)
    }

    override fun showPhoto(bitmap: Bitmap) {
        ivPhoto.setImageBitmap(bitmap)
    }

    override fun setupUI() {
    }

    override fun setupUX() {
        btnDelete.setOnClickListener { barrierDetailPresenter.removeBarrier() }
        btnModernization.setOnClickListener { startActivity(JivoActivity::class.java) }
        ivPhoto.setOnClickListener { showPictureDialog() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.barrier_detail_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> barrierDetailPresenter.updateBarrier(etName.text.toString(), etAddress.text.toString())
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showData(barrier: Barrier, photos: Photos) {
        etName.setText(barrier.name)
        etAddress.setText(barrier.address)
        ltModernization.visibility = if (barrier.isOld()) View.VISIBLE else View.INVISIBLE
        if (photos.containsKey(barrier.number))
            ImageLoader.load(this, ivPhoto, photos[barrier.number]!!)
    }

    @InjectPresenter
    lateinit var barrierDetailPresenter: BarrierDetailPresenter

    @ProvidePresenter
    internal fun provideBarrierDetailPresenter() = Injector.presenterComponent!!.barrierDetailPresenter

    companion object {

        private val LAYOUT = R.layout.activity_barrier_detail

        fun getIntent(context: Context): Intent {
            return Intent(context, BarrierDetailActivity::class.java)
        }
    }
}
