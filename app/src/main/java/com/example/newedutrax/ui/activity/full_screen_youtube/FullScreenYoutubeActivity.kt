package com.example.newedutrax.ui.activity.full_screen_youtube

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.example.newedutrax.R
import com.example.newedutrax.databinding.ActivityFullScreenYoutubeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo

class FullScreenYoutubeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFullScreenYoutubeBinding
    private lateinit var youTubePlayer: YouTubePlayer
    private var isFullScreen = false


    private val onBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            if (isFullScreen){
                youTubePlayer.toggleFullscreen()
            }else{
                finish()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_youtube)

        onBackPressedDispatcher.addCallback(onBackPressedCallback)


        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true
                binding.fullScreenContainer.visibility = View.VISIBLE
                binding.fullScreenContainer.addView(fullscreenView)

                // Full Screen remove status bar and navigation bar
                WindowInsetsControllerCompat(window!!,findViewById(R.id.main)).apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }

                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }

            }

            override fun onExitFullscreen() {
                isFullScreen = false
                binding.fullScreenContainer.visibility = View.GONE
                binding.fullScreenContainer.removeAllViews()

                // status bar and navigation bar
                WindowInsetsControllerCompat(window!!,findViewById(R.id.main)).apply {
                    show(WindowInsetsCompat.Type.systemBars())
                }
                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR){
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                }

            }
        })

        val youtubePlayerListener = object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@FullScreenYoutubeActivity.youTubePlayer = youTubePlayer
                val videoId = "RazWA1DN0Dw"
                youTubePlayer.loadOrCueVideo(lifecycle,videoId, 0f)
            }
        }

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1)
            .build()

        binding.youtubePlayerView.enableAutomaticInitialization = false
        binding.youtubePlayerView.initialize(youtubePlayerListener,iFramePlayerOptions)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (!isFullScreen){
                youTubePlayer.toggleFullscreen()
            }
        }else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if (isFullScreen){
                youTubePlayer.toggleFullscreen()
            }
        }
    }
}