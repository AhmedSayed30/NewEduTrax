    package com.example.newedutrax.ui.activity.course_player

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.newedutrax.R
import com.example.newedutrax.databinding.ActivityCoursePlayerBinding
import com.example.newedutrax.ui.fragment.home.HomeAdapter
import com.example.newedutrax.utils.SharedPrefUtils.getToken
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

    class CoursePlayerActivity : AppCompatActivity() {
        private lateinit var binding: ActivityCoursePlayerBinding
        private val viewModel = CoursePlayerViewModel()
        private val adapter: LessonAdapter by lazy {
            LessonAdapter {

            }
        }
        private var isFullScreen = false
        private lateinit var youTubePlayer: YouTubePlayer
        private val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isFullScreen) {

                    youTubePlayer.toggleFullscreen()
                } else {
                    finish()
                }
            }
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_course_player)
            val id = intent.getStringExtra("id")
            if (id != null) {
                viewModel.getCourseLec(getToken(binding.root.context), id)
            }
            viewModel.data.observe(
                this
            ) {
                adapter.changeData(it)
            }
            binding.rvLessons.adapter = adapter
//            checkState()
            onBackPressedDispatcher.addCallback(onBackPressedCallback)
            lifecycle.addObserver(binding.youtubePlayerView)
            binding.youtubePlayerView.addFullscreenListener(object : FullscreenListener {
                override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                    isFullScreen = true
                    binding.fullScreenContainer.visibility = View.VISIBLE
                    binding.fullScreenContainer.addView(fullscreenView)

                    // Full Screen remove status bar and navigation bar
                    WindowInsetsControllerCompat(window!!, findViewById(R.id.main)).apply {
                        hide(WindowInsetsCompat.Type.systemBars())
                        systemBarsBehavior =
                            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    }

                    if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                    }

                }

                override fun onExitFullscreen() {
                    isFullScreen = false
                    binding.fullScreenContainer.visibility = View.GONE
                    binding.fullScreenContainer.removeAllViews()

                    // status bar and navigation bar
                    WindowInsetsControllerCompat(window!!, findViewById(R.id.main)).apply {
                        show(WindowInsetsCompat.Type.systemBars())
                    }
                    if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                    }

                }
            })

            val youtubePlayerListener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    this@CoursePlayerActivity.youTubePlayer = youTubePlayer
                    val videoId = "https://youtu.be/tMe0vwZ13fw?si=P7qv7mujyti54PTF"
                    youTubePlayer.loadOrCueVideo(lifecycle, videoId, 0f)
                }
            }

            val iFramePlayerOptions = IFramePlayerOptions.Builder()
                .controls(1)
                .fullscreen(1)
                .build()

            binding.youtubePlayerView.enableAutomaticInitialization = false
            binding.youtubePlayerView.initialize(youtubePlayerListener, iFramePlayerOptions)

        }

        override fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (!isFullScreen) {
                    youTubePlayer.toggleFullscreen()
                }
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                if (isFullScreen) {
                    youTubePlayer.toggleFullscreen()
                }
            }
        }

        //val apply = binding.apply {
//            val url = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mEpoDlSGmdY?si=u0aLptXLOAl5gYo8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
//            web.loadData(url, "text/html", "utf-8")
//           web.settings.javaScriptEnabled = true
//            web.webChromeClient = WebChromeClient()
//            //web.webViewClient = WebViewClient()
//            web.settings.setSupportZoom(true)
//
//            //web.loadUrl(url)
//            //web.settings.javaScriptEnabled = true
//        private fun checkState() {
//            lifecycleScope.launch {
//                viewModel.state.collectLatest {
//                    if (it.error?.isNotEmpty() == true) Toast.makeText(
//                        binding.root.context,
//                        it.error.toString(),
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//
//
//        }
    }








