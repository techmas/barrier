package ru.techmas.barrier.utils

import android.content.Context
import android.widget.ImageView

import com.bumptech.glide.Glide

/**
 * Date: 04.06.2017
 * Time: 11:35
 * Project: Android Template

 * @author Alex Bykov
 * *         You can contact me at me@alexbykov.ru
 */
object ImageLoader {
    //@formatter:on


    fun load(context: Context, imageView: ImageView, url: String) {
        Glide.with(context)
                .load(url)
                .into(imageView)
        // TODO: 04.06.2017 PlaceHolder here and any logic

    }

}//@formatter:off
