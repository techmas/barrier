package ru.techmas.barrier.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
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

import ru.techmas.barrier.utils.Injector


class AddBarrierActivity : BaseSingleActivity(), AddBarrierView {

    private lateinit var cameraHelper: CameraHelper

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
        ivPhoto.setOnClickListener { getCameraPhoto() }
        btnAdd.setOnClickListener { addBarrierPresenter.addBarrier(
                etName.text.toString(),
                etAddress.text.toString(),
                etPhone.text.toString()) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraHelper.onActivityResult(requestCode, resultCode, data)
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
