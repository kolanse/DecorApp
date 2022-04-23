package com.example.decorapp

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.example.decorapp.databinding.SplashActivityBinding
import com.example.decorapp.ui.DashBoardActivity


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: SplashActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        handleClicks()
    }

    private fun handleClicks() {
        binding.circleCard.setOnClickListener {
            animateCircle()
        }
    }

    private fun animateCircle(){
        val animationSet = AnimatorSet()

        val scaleY = ObjectAnimator.ofFloat(binding.circleCard, "scaleY", 1f, 2.5f)
        val scaleX = ObjectAnimator.ofFloat(binding.circleCard, "scaleX", 1f, 2.5f)
        val alphaAnimation: ObjectAnimator = ObjectAnimator.ofFloat(binding.circleCard, View.ALPHA, 1f, 0.8f)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(),  ContextCompat.getColor(this, R.color.pink_50), ContextCompat.getColor(this, R.color.pink_50))

        colorAnimation.addUpdateListener { animator -> binding.circleCard.setCardBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
        animationSet.playTogether(scaleX, scaleY, alphaAnimation, colorAnimation)
        animationSet.duration = 1000
        animationSet.start()

        val handler = Handler()
        handler.postDelayed(Runnable {

            val i = Intent(this, DashBoardActivity::class.java)
            startActivity(i)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }, 700)
    }
}