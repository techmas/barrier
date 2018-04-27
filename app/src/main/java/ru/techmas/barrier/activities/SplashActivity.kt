package ru.techmas.barrier.activities

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter

import ru.techmas.barrier.R
import ru.techmas.barrier.interfaces.views.SplashView
import ru.techmas.barrier.presenters.SplashPresenter
import ru.techmas.barrier.utils.Injector
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import ru.alexbykov.nopermission.PermissionHelper


class SplashActivity : BaseActivity(), SplashView {

//    private var ltBackground: LinearLayout? = null
//    private var btnRepeat: Button? = null
//    private var tvSomethingWentWrong: TextView? = null
//    private var progressBar: ProgressBar? = null
    private lateinit var permissionHelper: PermissionHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        setupUI()
        setupUX()
        checkPermissions()
    }

    private fun checkPermissions() {
        permissionHelper = PermissionHelper(this)
        permissionHelper.check(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .onSuccess(Runnable { this.successPermission() })
                .onFailure(Runnable { this.failurePermission() })
                .run()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun failurePermission() {
        showError(getString(R.string.permission_info_rationale))
        checkPermissions()
    }

    private fun successPermission() {
        mvpDelegate.onAttach()
        splashPresenter.startNext()
    }

    override fun setupUX() {
//        btnRepeat!!.setOnClickListener { splashPresenter.startNext() }
    }

    override fun showErrorConnection(show: Boolean) {
//        if (show) {
//            hideView(progressBar!!)
//            showView(ltBackground!!)
//            animationHelper.scaleIn(tvSomethingWentWrong!!)
//        } else
//            hideView(ltBackground!!)
    }

    override fun setupUI() {
//        ltBackground = bindView<LinearLayout>(R.id.ltBackground)
//        btnRepeat = bindView<Button>(R.id.btnRepeat)
//        tvSomethingWentWrong = bindView<TextView>(R.id.tvSomethingWentWrong)
//        progressBar = bindView<ProgressBar>(R.id.progressBar)
    }

    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    @ProvidePresenter
    internal fun provideSplashPresenter() = Injector.presenterComponent!!.splashPresenter

    companion object {
        private const val LAYOUT = R.layout.activity_splash
    }

}
