# WebToApkVD V1

Native Android WebView website-to-APK starter.

## Included
- Neon Brutalism index.html
- Online URL builder UI
- Offline ZIP builder UI
- Native Kotlin WebView template
- GitHub Actions release APK workflow
- Configuration injection
- Repository configuration

## Build
Push to GitHub, open Actions, choose `WebToApkVD - Build APK`, then Run workflow.

## Important
The frontend is static and does not contain secrets. A public one-click builder needs a secure backend to trigger GitHub Actions. Offline ZIP builds also need a secure upload path into the build worker.

Production deployments should isolate builds, validate URLs and ZIP archives, limit CPU/RAM/time, and delete temporary files.

## License
MIT