package ru.techmas.barrier.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle

import ru.techmas.barrier.interfaces.views.AuthView
import ru.techmas.barrier.presenters.AuthPresenter
import ru.techmas.barrier.R

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_auth.*
import ru.techmas.barrier.utils.Injector
import android.view.inputmethod.EditorInfo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.techmas.barrier.Const
import java.util.concurrent.TimeUnit
import android.text.Editable
import android.text.TextWatcher




class AuthActivity : BaseActivity(), AuthView {

    private var seconds = Const.Time.REPEAT_SMS

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(LAYOUT)
        super.onCreate(savedInstanceState)
    }

    override fun showCode() {
        startTimer()
        hideView(btnGetSmsCode)
        showView(llSmsCode)
    }

    private fun startTimer() {
        showSeconds(seconds)
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnEach { getSeconds().subscribe { i -> showSeconds(i) }}
                .takeWhile { seconds > 0 }
                .doOnComplete { showRepeatSms() }
                .subscribe()
    }

    private fun showRepeatSms() {
        seconds = Const.Time.REPEAT_SMS
        showView(btnGetSmsCode)
        hideView(llSmsCode)
    }

    private fun showSeconds(seconds: Int) {
        tvSeconds.text = resources.getString(R.string.repeat_sms_code, seconds)
    }

    private fun getSeconds(): Single<Int>  {
        return Single.fromCallable { --seconds }
    }
    override fun setupUI() {
    }

    override fun setupUX() {
        btnGetSmsCode.setOnClickListener { authPresenter.sendSms(etPhone.text.toString()) }
        ivSupport.setOnClickListener { authPresenter.supportClick() }
//        etPhone.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
//                etPhone.setText("+7")
//            }
//            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//            override fun afterTextChanged(editable: Editable) {
//            }
//        })
        etSmsCode.setOnFocusChangeListener { v, hasFocus -> authPresenter.checkCode(etSmsCode.text.toString(), hasFocus)}
        etSmsCode.setOnEditorActionListener { textView, actionId, keyEvent ->
            val result = actionId and EditorInfo.IME_MASK_ACTION
            when (result) {
                EditorInfo.IME_ACTION_DONE -> {
                    authPresenter.checkCode(etSmsCode.text.toString(), false)
                }
            }
            false
        }
    }

    @InjectPresenter
    lateinit var authPresenter: AuthPresenter

    @ProvidePresenter
    internal fun provideAuthPresenter() = Injector.presenterComponent!!.authPresenter

    companion object {
        private const val LAYOUT = R.layout.activity_auth

        fun getIntent(context: Context): Intent {
            return Intent(context, AuthActivity::class.java)
        }
    }
}
