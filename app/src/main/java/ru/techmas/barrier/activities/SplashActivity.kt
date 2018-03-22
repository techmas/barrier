package ru.techmas.barrier.activities

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


class SplashActivity : BaseActivity(), SplashView {

//    private var ltBackground: LinearLayout? = null
//    private var btnRepeat: Button? = null
//    private var tvSomethingWentWrong: TextView? = null
//    private var progressBar: ProgressBar? = null

    @InjectPresenter
    lateinit var splashPresenter: SplashPresenter

    @ProvidePresenter
    internal fun provideSplashPresenter() = Injector.presenterComponent!!.splashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(LAYOUT)
        setupUI()
        setupUX()
        mvpDelegate.onAttach()
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

    companion object {
        private const val LAYOUT = R.layout.activity_splash
    }

}
