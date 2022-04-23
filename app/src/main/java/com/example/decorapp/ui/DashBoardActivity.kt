package com.example.decorapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.decorapp.*
import com.example.decorapp.databinding.ActivityDashBoardBinding
import com.example.decorapp.databinding.SplashActivityBinding

class DashBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }


    override fun onResume() {
        super.onResume()
        // delay transition as transitions are not called until view is attached to window
        var handler = Handler()
        handler.postDelayed(Runnable {
           handleBottomNav()
        }, 100)

    }


    private fun handleBottomNav() {
        binding.apply {
            val navController = Navigation.findNavController(this@DashBoardActivity, R.id.nav_host_fragment)
            bottomNav.setupWithNavController(navController)



            // Hide bottom nav on screens which don't require it
            lifecycleScope.launchWhenResumed {
                navController.addOnDestinationChangedListener { _, destination, _ ->
                    when (destination.id) {
                        R.id.productDetailFragment -> {
                            binding.root.transitionToStart()
                        }
                        else -> {
                           binding.root.transitionToEnd()
                        }
                    }
                }
            }
        }
    }
}