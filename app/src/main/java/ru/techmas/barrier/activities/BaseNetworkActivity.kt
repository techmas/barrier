package ru.techmas.barrier.activities

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

import ru.techmas.barrier.interfaces.utils_view.NetworkView

/**
 * Date: 04.06.2017
 * Time: 12:17
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
abstract class BaseNetworkActivity : BaseActivity(), NetworkView {


    private val ltData: ViewGroup? = null
    private val progressBar: ProgressBar? = null
    private val btnRepeat: Button? = null
    private val ltError: ViewGroup? = null
    private val tvError: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            showView(progressBar!!)
        } else {
            hideView(progressBar!!)
        }
    }

    override fun showData(show: Boolean) {
        if (show){
            showView(ltData!!)
        }
        else{
            hideView(ltData!!)
        }
    }

    override fun showError(show: Boolean, text: String) {
        if (show) {
            showView(ltError!!)
            tvError!!.text = text
        } else
            hideView(ltError!!)
    }
}
