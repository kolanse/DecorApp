package com.example.decorapp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.view.View
import android.view.animation.*
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.DynamicAnimation.ViewProperty
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.launch


inline fun Fragment.performAnimation(crossinline block: suspend () -> Unit) {
   lifecycleScope.launch {

    }
}

fun View.spring(
    property: DynamicAnimation.ViewProperty,
    stiffness: Float = 200f,
    damping: Float = 0.3f,
    startVelocity: Float? = null
): SpringAnimation {
    val key = getKey(property)
    var springAnim = getTag(key) as? SpringAnimation?
    if (springAnim == null) {
        springAnim = SpringAnimation(this, property).apply {
            spring = SpringForce().apply {
                this.dampingRatio = damping
                this.stiffness = stiffness
                startVelocity?.let { setStartVelocity(it) }
            }
        }
        setTag(key, springAnim)
    }
    return springAnim
}

/**
 * Map from a [ViewProperty] to an `id` suitable to use as a [View] tag.
 */
@IdRes
private fun getKey(property: ViewProperty): Int {
    return when (property) {
        SpringAnimation.TRANSLATION_X -> R.id.translation_x
        SpringAnimation.TRANSLATION_Y -> R.id.translation_y
        SpringAnimation.TRANSLATION_Z -> R.id.translation_z
        SpringAnimation.SCALE_X -> R.id.scale_x
        SpringAnimation.SCALE_Y -> R.id.scale_y
        SpringAnimation.ROTATION -> R.id.rotation
        SpringAnimation.ROTATION_X -> R.id.rotation_x
        SpringAnimation.ROTATION_Y -> R.id.rotation_y
        SpringAnimation.X -> R.id.x
        SpringAnimation.Y -> R.id.y
        SpringAnimation.Z -> R.id.z
        SpringAnimation.ALPHA -> R.id.alpha
        SpringAnimation.SCROLL_X -> R.id.scroll_x
        SpringAnimation.SCROLL_Y -> R.id.scroll_y
        else -> throw IllegalAccessException("Unknown ViewProperty: $property")
    }
}

/**
 * Show bottom Nav
 */
fun BottomNavigationView.show() {
    visibility = View.VISIBLE

}


fun BottomNavigationView.hide() {
    visibility = View.GONE
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}


/**
 * Give Side Margins to Viewpager items
 */
fun ViewPager2.setShowSideItems(pageMarginPx : Int, offsetPx : Int) {

    clipToPadding = false
    clipChildren = false
    offscreenPageLimit = 3

    setPageTransformer { page, position ->

        val offset = position * -(2 * offsetPx + pageMarginPx)
        if (this.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
    }
}


fun ShapeableImageView.reduce(){
    this.strokeWidth = 0f
    val pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f, 1f)
    val pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f, 1f)
    val scaleAnimation: ObjectAnimator =
        ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY)

    val setAnimation = AnimatorSet()
    setAnimation.play(scaleAnimation)
    setAnimation.start()
}

fun ShapeableImageView.increase(){

    this.strokeWidth = 25f
    val pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.2f)
    val pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.2f)
    val scaleAnimation: ObjectAnimator =
        ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY)

    val setAnimation = AnimatorSet()
    setAnimation.play(scaleAnimation)
    setAnimation.start()
}

