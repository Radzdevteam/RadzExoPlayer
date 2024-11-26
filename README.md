# Radz ExoPlayer
[![](https://jitpack.io/v/Radzdevteam/RadzExoPlayer.svg)](https://jitpack.io/#Radzdevteam/RadzExoPlayer)

Radz ExoPlayer is a media player library developed by Radz, leveraging the powerful ExoPlayer framework.

## Player Supported Formats:
- HLS (HTTP Live Streaming)
- M3U8
- MP4
- TS
- DASH (Dynamic Adaptive Streaming over HTTP)
- TLS (Transport Layer Security)

## How to Include
### Step 1. Add the repository to your project settings.gradle:
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
   ```

### Step 2. Add the dependency
```groovy
dependencies {
     implementation ("com.github.Radzdevteam:RadzExoPlayer:Tag")
}

   ```

## Usage

In your `MainActivity`, add the following code:
```groovy
"videoUrl"
"subtitleUrl"

   ```

Example
```groovy
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

   ```
