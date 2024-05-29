@file:Suppress("DEPRECATION")

package com.radzdev.radzexoplayer

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackParameters
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.media3.exoplayer.DefaultRenderersFactory
import kotlin.math.max

class ExoPlayerManager : AppCompatActivity() {

    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: ExoPlayer
    private lateinit var btnSpeed: ImageView
    private lateinit var exoplayerResize: ImageView
    private lateinit var playPauseButton: ImageView
    private lateinit var audioDetails: ImageView
    private lateinit var rotateButton: ImageView
    private lateinit var lockButton: ImageView
    private lateinit var forwardButton: ImageView
    private lateinit var backwardButton: ImageView
    private lateinit var onBackPressLayout: LinearLayout
    private lateinit var titleTextView: TextView
    private var isPlayerLocked = false

    @SuppressLint("InflateParams", "PrivateResource", "MissingInflatedId")
    @UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            window?.attributes?.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        setContentView(R.layout.activity_exo_player_manager)
        playerView = findViewById(R.id.player_view)
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
        btnSpeed = playerView.findViewById(R.id.exo_playback_speed)
        exoplayerResize = playerView.findViewById(R.id.screen_resize)
        playPauseButton = playerView.findViewById(R.id.exo_play_pause)
        audioDetails = playerView.findViewById(R.id.exo_audio_details)
        rotateButton = playerView.findViewById(R.id.rotateButton)
        lockButton = playerView.findViewById(R.id.lockButton)
        forwardButton = playerView.findViewById(R.id.fwd)
        backwardButton = playerView.findViewById(R.id.rew)
        onBackPressLayout = findViewById(R.id.OnBackPress)
        titleTextView = playerView.findViewById(R.id.exo_title)

        onBackPressLayout.setOnClickListener {
            finish()
        }

        playPauseButton.setOnClickListener {
            if (exoPlayer.isPlaying) {
                exoPlayer.pause()
            } else {
                exoPlayer.play()
            }
        }

        rotateButton.setOnClickListener {
            val currentOrientation = resources.configuration.orientation
            requestedOrientation = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            } else {
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }

        lockButton.setOnClickListener {
            isPlayerLocked = !isPlayerLocked
            lockPlayerView(isPlayerLocked)

            if (isPlayerLocked) {
                lockButton.setImageResource(R.drawable.lock)
                showToastMessage("Player Locked")
            } else {
                lockButton.setImageResource(R.drawable.unlock)
                showToastMessage("Player Unlocked")
            }
        }

        forwardButton.setOnClickListener {
            val currentPosition = exoPlayer.currentPosition
            exoPlayer.seekTo(currentPosition + 10000)
        }

        backwardButton.setOnClickListener {
            val currentPosition = exoPlayer.currentPosition
            exoPlayer.seekTo(max(0, currentPosition - 10000))
        }

        val mediaUrl = intent.getStringExtra("videoUrl")!!
        val customTitle = intent.getStringExtra("customTitle")!!

        titleTextView.text = customTitle

        val renderersFactory = DefaultRenderersFactory(this)
            .setExtensionRendererMode(DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
        exoPlayer = ExoPlayer.Builder(this, renderersFactory).build()
        playerView.player = exoPlayer

        val mediaItem = MediaItem.fromUri(mediaUrl)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        audioDetails.setOnClickListener {
            val audioFormat = exoPlayer.audioFormat
            if (audioFormat != null) {
                val sampleRate = audioFormat.sampleRate
                val channels = audioFormat.channelCount
                val audioType = audioFormat.sampleMimeType
                val audioDetails = "Audio Sample Rate: $sampleRate\n" +
                        "Audio Channels: $channels\n" +
                        "Audio Type: $audioType"

                val dialog = AlertDialog.Builder(this)
                    .setTitle("Audio Tracks")
                    .setMessage(audioDetails)
                    .setPositiveButton("OK", null)
                    .create()

                dialog.show()

            } else {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Audio Tracks")
                    .setMessage("None")
                    .setPositiveButton("OK", null)
                    .create()

                dialog.show()

            }
        }

        setupControls()

        exoplayerResize.setOnClickListener {
            val currentResizeMode = playerView.resizeMode
            val nextResizeMode = when (currentResizeMode) {
                AspectRatioFrameLayout.RESIZE_MODE_FILL -> {
                    exoplayerResize.setImageResource(R.drawable.fullscreen)
                    showToastMessage("Fit Mode")
                    AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
                AspectRatioFrameLayout.RESIZE_MODE_FIT -> {
                    exoplayerResize.setImageResource(R.drawable.full_screen_zoom)
                    showToastMessage("Zoom Mode")
                    AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                }
                else -> {
                    exoplayerResize.setImageResource(R.drawable.fullscreen)
                    showToastMessage("Fit Mode")
                    AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            }
            playerView.resizeMode = nextResizeMode
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this@ExoPlayerManager, message, Toast.LENGTH_SHORT).show()
    }

    private fun lockPlayerView(isLocked: Boolean) {
        if (isLocked) {
            playPauseButton.isEnabled = false
            btnSpeed.isEnabled = false
            exoplayerResize.isEnabled = false
            audioDetails.isEnabled = false
            rotateButton.isEnabled = false
            forwardButton.isEnabled = false
            backwardButton.isEnabled = false
        } else {
            playPauseButton.isEnabled = true
            btnSpeed.isEnabled = true
            exoplayerResize.isEnabled = true
            audioDetails.isEnabled = true
            rotateButton.isEnabled = true
            forwardButton.isEnabled = true
            backwardButton.isEnabled = true
        }
    }

    private fun setupControls() {
        btnSpeed.setOnClickListener {
            val builder = AlertDialog.Builder(this@ExoPlayerManager)
            builder.setTitle("Set Speed")
            val speeds = arrayOf("0.25X", "0.5X", "1X", "1.5X", "2X")
            builder.setItems(speeds) { _, which ->
                val speed = when (which) {
                    0 -> 0.25f
                    1 -> 0.5f
                    2 -> 1f
                    3 -> 1.5f
                    4 -> 2f
                    else -> 1f
                }
                exoPlayer.playbackParameters = PlaybackParameters(speed)
            }
            builder.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }
}
