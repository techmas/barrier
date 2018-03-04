package ru.techmas.barrier.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_support.*
import ru.techmas.barrier.R

/**
 * Created by natalia on 01.03.18.
 */

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        setOnClickListener()
    }

    fun setOnClickListener () {
        tvBack.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
