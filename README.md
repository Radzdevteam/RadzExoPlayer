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
     implementation ("com.github.Radzdevteam:RadzExoPlayer:tag")
}

   ```

## Usage

In your `MainActivity`, add the following code:
```groovy
"videoUrl"
"customTitle"

   ```

Example
```groovy
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startExoPlayerManager()
    }

    private fun startExoPlayerManager() {
        val videoUrl = "http://122.55.252.134:8443/live/bba5b536faeacb9b56a3239f1ee8e3b3/1.m3u8"
        val customTitle = "Test 123"

        val intent = Intent(this, ExoPlayerManager::class.java)
        intent.putExtra("videoUrl", videoUrl)
        intent.putExtra("customTitle", customTitle)
        startActivity(intent)
    }
}

   ```

## Manifest
In your `AndroidManifest`, add the following code:
```groovy
 <activity android:name="com.radzdev.radzexoplayer.ExoPlayerManager" />
   ```
