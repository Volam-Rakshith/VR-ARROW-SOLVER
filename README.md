# VR ARROW SOLVER — Android starter (v0.1.0)

A free, offline Android companion project under VR DEVELOPMENTS.

## What is included
- Native Android/Kotlin starter app.
- Local arrow-removal sequence solver.
- A test screen to exercise the solver without connecting to a game.
- No account, backend, cloud storage, paid API, or in-app ads.

## Important status
This is the **first development milestone**, not a finished auto-player. The current test board is manually defined. It does not yet capture another app's screen, recognise arrows from pixels, or tap the game.

## Planned milestones
1. Calibrate arrow detection using screenshots from the target game.
2. Add Android MediaProjection screen capture with a clear system permission prompt.
3. Detect arrow connected components and infer direction/position.
4. Add a review screen showing the proposed sequence.
5. Add an opt-in Accessibility Service to tap only the puzzle arrows, with pause/stop controls.
6. Test across levels and screen sizes.

## Build
Open this folder in Android Studio (Giraffe or newer), allow Gradle sync, then run on an Android 8.0+ device.

## Safety and permissions
Screen capture and accessibility tapping are sensitive capabilities. They must be explicitly enabled by the device owner. The app will not automate ad views, ad clicks, or reward claims. Keep ad interactions manual and follow the game's rules.

## Solver assumption
The initial solver models each arrow as a small bounding rectangle and assumes an arrow can exit when no other remaining arrow intersects its forward path. This must be validated against the actual game's mechanics before enabling automatic taps.


## Build an APK using only your phone (no Android Studio)
1. Create a free GitHub repository and upload the contents of this folder (including the hidden `.github` folder).
2. Open the repository's **Actions** tab.
3. Select **Build Android APK** and tap **Run workflow** (or wait for the first push build).
4. When the run finishes, open it and download the **VR-Arrow-Solver-debug** artifact.
5. Extract the downloaded artifact ZIP to get `app-debug.apk`, then install it on your Android phone.

This cloud build workflow compiles the current starter app only. It does not add live screen recognition or automatic game tapping yet.
