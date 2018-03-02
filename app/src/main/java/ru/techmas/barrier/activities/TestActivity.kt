package ru.techmas.barrier.activities

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_autorization.*
import ru.techmas.barrier.R

/**
 * Created by natalia on 01.03.18.
 */

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        addListenerOnButton()
    }

    fun addListenerOnButton() {
        btnGetSmsCode.setOnClickListener { showSms(it) }
    }

    private fun showSms(v: View) {
        v.visibility = View.GONE
        llSmsCode.visibility = View.VISIBLE
    }
}
