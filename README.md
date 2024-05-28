# Radz ExoPlayer
[![](https://jitpack.io/v/Radzdevteam/RadzExoPlayer.svg)](https://jitpack.io/#Radzdevteam/RadzExoPlayer)

Radz ExoPlayer is a media player library developed by Radz, leveraging the powerful ExoPlayer framework.

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
     implementation ("com.github.Radzdevteam:RadzExoPlayer:1.5")
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
