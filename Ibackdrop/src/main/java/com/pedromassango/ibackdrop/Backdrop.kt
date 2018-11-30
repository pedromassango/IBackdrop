package com.pedromassango.ibackdrop

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat

class Backdrop(context: Context,
               attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private lateinit var toolbar: Toolbar
    private var openIcon: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_drop_open, null)
    private var closeIcon: Drawable? = ResourcesCompat.getDrawable(resources, R.drawable.ic_drop_close, null)
    private var frontLayerBackground: Int = R.drawable.backdrop_background
    private var backdropSize: Int = 0
    private lateinit var navIconClickListener: NavigationIconClickListener

    private var mcToolbarId: Int

    init {
        val customProperties = context.obtainStyledAttributes(attributeSet, R.styleable.Backdrop)

        try {
            val moIcon: Drawable? = customProperties.getDrawable(R.styleable.Backdrop_openIcon)
            val mcIcon: Drawable? = customProperties.getDrawable(R.styleable.Backdrop_closeIcon)
            val mBackdropSize: Int? = customProperties.getDimensionPixelSize(R.styleable.Backdrop_backViewSize, backdropSize)
            val mTopRightRadius: Boolean? = customProperties.getBoolean(R.styleable.Backdrop_removeTopRightRadius, false)
            mcToolbarId = customProperties.getResourceId(R.styleable.Backdrop_toolbar, -1)
            moIcon?.let { openIcon = moIcon }
            mcIcon?.let { closeIcon = mcIcon }
            mBackdropSize?.let { backdropSize = it }
            mTopRightRadius?.let {
                frontLayerBackground = if (mTopRightRadius) R.drawable.backdrop_background_round_left else R.drawable.backdrop_background
            }
        } finally {
            customProperties.recycle()
        }
    }

    /**
     * Build the backdrop view.
     *
     * NOTE: Require Toolbar is initialized with reference
     */
    private fun build() {
        setToolbarWithReference()
        // click listener to open/close the sheet
        navIconClickListener = NavigationIconClickListener(context,
                backView = getBackView(),
                sheet = getFrontView(),
                backdropSize = backdropSize,
                interpolator = LinearInterpolator(),
                openIcon = openIcon,
                closeIcon = closeIcon
        )

        // on toolbar navigation click, handle it
        toolbar.setNavigationOnClickListener(navIconClickListener)
    }

    private fun setToolbarWithReference() {
        if (mcToolbarId == -1) throw IllegalStateException("Set toolbar property on XML or use Backdrop#buildWithToolbar(Toolbar)")
        val view: View? = rootView.findViewById(mcToolbarId)
        when (view) {
            null -> throw IllegalStateException("View does not accessible")
            !is Toolbar -> throw IllegalStateException("View is not Toolbar")
            else -> toolbar = view
        }
    }

    /**
     * Build the backdrop view with Toolbar.
     */
    fun buildWithToolbar(toolbar: Toolbar) {
        setToolbar(toolbar)

        // click listener to open/close the sheet
        navIconClickListener = NavigationIconClickListener(context,
                backView = getBackView(),
                sheet = getFrontView(),
                backdropSize = backdropSize,
                interpolator = LinearInterpolator(),
                openIcon = openIcon,
                closeIcon = closeIcon
        )

        // on toolbar navigation click, handle it
        toolbar.setNavigationOnClickListener(navIconClickListener)
    }

    private fun setToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        // update open icon
        this.toolbar.navigationIcon = openIcon
    }

    /**
     * Call this function will open the backdrop.
     *
     * NOTE: this will open, only if it is currently closed.
     */
    fun openBackdrop() = navIconClickListener.open()

    /**
     * Call this function will close the backdrop
     *
     * NOTE: this will close, nly if it is currently opened.
     */
    fun closeBackdrop() = navIconClickListener.close()

    /**
     * Here whe check if there is more than two child views.
     * If true, we throw an exception in runtime.
     * @throws IllegalArgumentException if there is more than two child views.
     *
     * And change the front view background color.
     */
    override fun onFinishInflate() {
        super.onFinishInflate()

        // if there is more than two views, crash the execution
        if (childCount <= 1 || childCount > 2) {
            throw IllegalArgumentException(" ${this.javaClass.simpleName} must contain two child views!")
        }

        getFrontView().background = ResourcesCompat.getDrawable(resources, frontLayerBackground, null)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (mcToolbarId != -1) {
            build()
        }
    }

    /**
     * Function to return the back view.
     * @return the first view in this layout.
     */
    private fun getBackView(): View = getChildAt(0)

    /**
     * Function to return the backdrop view.
     * @return the second view in this layout.
     */
    private fun getFrontView(): View = getChildAt(1)

}