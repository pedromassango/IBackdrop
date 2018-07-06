package com.pedromassango.ibackdrop

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import android.widget.ImageView

class NavigationIconClickListener @JvmOverloads internal constructor(
        context: Context,
        private val backView: View,
        private val sheet: View,
        private val interpolator: Interpolator? = null,
        private val openIcon: Drawable? = null,
        private val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private val animDuration = 550L
    private var height: Int
    private var backdropShown = false

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = (displayMetrics.heightPixels)
    }

    override fun onClick(view: View) {
        backdropShown = !backdropShown

        // reduce backdrop folded height by reduce the toolbar with & status bar height
        val size = backView.width + view.height + 16
        val translateY = height - size

        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        // menu icon update
        updateIcon(view)

        // [START] animation
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = animDuration
        interpolator?.let{interpolator ->
            animator.interpolator = interpolator
        }

        // play the animation
        animatorSet.play(animator)
        animator.start()

        if(backdropShown){
            sheet.alpha = 0.9F
        }else{
            sheet.alpha = 1.0F
        }
    }

    /**
     * Update the Toolbar icon
     * @param view the clicked view. This must be a ImageView.
     */
    private fun updateIcon(view: View) {
        if (openIcon != null && closeIcon != null) {
            if (view !is ImageView) {
                throw IllegalArgumentException("updateIcon() must be called on an ImageView")
            }
            when(backdropShown) {
                true -> view.setImageDrawable(closeIcon)
                false -> view.setImageDrawable(openIcon)
            }
        }
    }
}