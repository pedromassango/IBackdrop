package com.pedromassango.ibackdrop

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Interpolator
import androidx.appcompat.widget.AppCompatImageButton

class NavigationIconClickListener @JvmOverloads internal constructor(
        context: Context,
        private val backView: View,
        private val sheet: View,
        private val backdropSize: Int,
        private val animDuration: Long = 200L,
        private val interpolator: Interpolator? = null,
        private val openIcon: Drawable? = null,
        private val closeIcon: Drawable? = null) : View.OnClickListener {

    private val animatorSet = AnimatorSet()
    private var height: Int
    private var backdropShown = false
    private var toolbarNavIcon: AppCompatImageButton? = null

    init {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = (displayMetrics.heightPixels)
    }

    fun open() = if(!backdropShown){ onClick(toolbarNavIcon!!) } else {}
    fun close() = if(backdropShown){ onClick(toolbarNavIcon!!) } else {}

    override fun onClick(view: View) {
        // only bind once
        if(toolbarNavIcon == null) {
            this.toolbarNavIcon = view as AppCompatImageButton
        }

        backdropShown = !backdropShown

        // reduce backdrop folded height by reduce the toolbarNavIcon with & status bar height
        val size = backView.width + view.height + 16

        // if no backdrop size provided, divide by the screen height
        val translateY = when(backdropSize != 0){
            true -> (height - backdropSize)
            else -> (height - size)
        }

        // Cancel the existing animations
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()

        // menu icon update
        updateIcon()

        // [START] animation
        val animator = ObjectAnimator.ofFloat(sheet, "translationY", (if (backdropShown) translateY else 0).toFloat())
        animator.duration = animDuration
        interpolator?.let{interpolator ->
            animator.interpolator = interpolator
        }

        // play the animation
        animatorSet.play(animator)
        animator.start()

        // add transparency on front layer when folded
        // we do not need this, because the front layer stay in the bottom of back layer
      /*  if(backdropShown){
            sheet.alpha = 0.9F
        }else{
            sheet.alpha = 1.0F
        }*/
    }

    /**
     * Update the Toolbar icon
     * @param view the clicked view. This must be a ImageView.
     */
    private fun updateIcon() {
        checkNotNull(toolbarNavIcon)

        if (openIcon != null && closeIcon != null) {
            when(backdropShown) {
                true -> toolbarNavIcon!!.setImageDrawable(closeIcon)
                false -> toolbarNavIcon!!.setImageDrawable(openIcon)
            }
        }
    }
}