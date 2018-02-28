package ru.techmas.barrier.custom.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

import ru.techmas.barrier.utils.ImageLoader

/**
 * Created by reg on 09.04.2017.
 */

class CustomImageView : ImageView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    fun load(URL: String) {
        ImageLoader.load(context, this, URL)
    }


}
