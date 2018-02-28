package ru.techmas.barrier.utils

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

import ru.techmas.barrier.R
import ru.techmas.barrier.custom.elements.SimpleAnimationListener


/**
 * Created by reg on 03.02.2017.
 */
class AnimationHelper(private val context: Context) {
    private var moveUp: TranslateAnimation? = null
    private var fadeIn: AlphaAnimation? = null
    private var fadeOut: AlphaAnimation? = null
    private var scaleIn: Animation? = null
    private var scaleOut: Animation? = null


    fun fadeIn(view: View) {

        if (fadeIn == null) {
            fadeIn = AlphaAnimation(0.1f, 1f)
            fadeIn!!.duration = 1000
            fadeIn!!.interpolator = DecelerateInterpolator()
        }
        fadeIn!!.setAnimationListener(object : SimpleAnimationListener() {
            override fun onAnimationEnd(animation: Animation) {
                view.clearAnimation()
            }
        })
        view.startAnimation(fadeIn)
    }

    fun fadeOut(view: View) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.tabs_fade_out))
        if (fadeOut == null) {
            fadeOut = AlphaAnimation(1f, 0f)
            fadeOut!!.duration = 1000
            fadeOut!!.interpolator = AccelerateInterpolator()
        }
        fadeOut!!.setAnimationListener(object : SimpleAnimationListener() {
            override fun onAnimationEnd(animation: Animation) {
                view.clearAnimation()
            }
        })
        view.startAnimation(fadeOut)
    }


    fun scaleIn(view: View) {
        if (scaleIn == null) {
            scaleIn = AnimationUtils.loadAnimation(context, R.anim.scale_in)
        }
        view.startAnimation(scaleIn)
    }


    fun scaleOut(view: View) {
        if (scaleOut == null) {
            scaleOut = AnimationUtils.loadAnimation(context, R.anim.scale_out)
        }
        view.startAnimation(scaleOut)
    }


    fun moveUp(view: View) {
        if (moveUp == null) {
            moveUp = TranslateAnimation(0f, 0f, 0f, -35f)
            moveUp!!.duration = 400
            moveUp!!.interpolator = DecelerateInterpolator()
            moveUp!!.fillAfter = true
        }
        view.startAnimation(moveUp)
    }
}
