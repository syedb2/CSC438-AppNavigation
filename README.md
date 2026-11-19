# App Navigation

CSC 438 — Syed Wajid Bukhari

[GitHub repository](https://github.com/syedb2/CSC438-AppNavigation)

The home page shows a scrollable list of ten solar-system images and descriptions. The Contact button opens a second page with a picture, name, college, and GitHub contact. The top-left back arrow returns to the home page and preserves the list position. Android's system back button also returns home.

Navigation follows the Week 10 lecture: enum routes, a NavController, a NavHost with composable destinations, and navigation through button callbacks. The list keeps the Week 8 model/data structure and LazyColumn.

Open this folder in Android Studio, let Gradle sync, and run the app on an Android 7.0 (API 24) or newer device. Use Android Studio's bundled JDK 17 or newer compatible JDK. The project uses compile SDK 35.

Screenshots are in `docs/screenshots`. Image credits are in `IMAGE_SOURCES.md`.
