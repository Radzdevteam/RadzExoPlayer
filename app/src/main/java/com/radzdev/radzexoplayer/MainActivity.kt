package com.radzdev.radzexoplayer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.radzdev.radzexoplayer.ui.theme.RadzExoPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Replace with your actual video URL and subtitle URL
        val videoUrl = "https://tralvoxmoon.xyz/file2/620jcDsH1AgYqsBfX9I++Q570Jo2OYVIp0zDZDaiW4SM5ptDF5BC7b2BQO~AfcP31CC4cong5TH6yaXnWdYeXG4+Evwb2WKD3R9~4Eb7C+x4BL8CUQ7mL1Z+a7TzeOa3QtCeqaX0aR4fQNV0RA1r~vkABGBq4a8Nf2BKaP8VruA=/cGxheWxpc3QubTN1OA==.m3u8"
        val subtitleUrl = "https://cca.megafiles.store/25/fa/25facaa955b601a0eaba00ac838db1b1/eng-2.vtt"

        // Create the intent to start ExoPlayerManager
        val intent = Intent(this, ExoPlayerManager::class.java).apply {
            putExtra("videoUrl", videoUrl)
            putExtra("subtitleUrl", subtitleUrl) // Optional, can be null if no subtitle
        }

        startActivity(intent)

        finish()
    }
}