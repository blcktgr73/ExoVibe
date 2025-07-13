# ExoVibe

ExoVibe is a simple Android application that allows users to pick, play, and keep track of recently played videos. The app uses the Android Storage Access Framework for secure file access and persists a list of recently played videos with their file names.

## Features

- **Pick Video:** Select a video file from your device using the system file picker.
- **Play Video:** Watch the selected video in a built-in player.
- **Recent Videos List:** View a vertically scrolling list of recently played videos, each showing an icon and the file name.
- **Persistent History:** The recent videos list is saved and restored between app launches.
- **Tap to Replay:** Tap any recent video to play it again.

## Screenshots

<!-- Add screenshots here if available -->
<!-- ![Main Screen](screenshots/main.png) -->

## Getting Started

### Prerequisites

- Android Studio (Giraffe or newer recommended)
- Android device or emulator (API 24+)

### Setup

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/ExoVibe.git
    cd ExoVibe
    ```

2. **Open in Android Studio:**
    - Open the project folder in Android Studio.

3. **Build and Run:**
    - Click "Run" or use `Shift+F10` to build and launch the app on your device/emulator.

## Usage

1. Tap **동영상 선택** ("Pick Video") to choose a video file.
2. The video will play in a new screen.
3. The file name will appear in the recent videos list.
4. Tap any recent video in the list to play it again.

## Project Structure

```
app/
  └── src/
      └── main/
          ├── java/com/blacktiger/exovibe/
          │   ├── MainActivity.kt
          │   ├── PlayerActivity.kt
          │   └── RecentVideosAdapter.kt
          └── res/
              ├── layout/
              │   ├── activity_main.xml
              │   └── item_recent_video.xml
              └── ...
```

---

You can further customize this README with screenshots, links, or additional sections as needed!

- **MainActivity:** Main screen, video picker, and recent videos list.
- **PlayerActivity:** Plays the selected video.
- **RecentVideosAdapter:** RecyclerView adapter for recent videos.

## Permissions

- The app uses the Storage Access Framework (`ACTION_OPEN_DOCUMENT`) and persists URI permissions for videos you pick. No special storage permissions are required.

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

---

**Made with ❤️ for Android.**
