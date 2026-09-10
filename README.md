# webtoapkvd

Project Android ini dibuat otomatis oleh ANBuildWeb. Isinya project Gradle Android asli
(bukan simulasi) dengan package ID "com.webtoapk.apk".

## Cara mendapatkan file APK asli

1. Buat repository baru di GitHub (boleh public atau private).
2. Upload/push semua isi folder ini ke repository tersebut.
3. Buka tab Actions di repo — workflow "Build APK" akan otomatis berjalan
   (kalau tidak otomatis, klik "Run workflow" secara manual).
4. Tunggu sampai selesai (kurang lebih 2-5 menit untuk build pertama).
5. Buka run yang sudah selesai, scroll ke bagian Artifacts, lalu unduh "app-release".
6. Extract ZIP artifact tersebut — di dalamnya ada file .apk yang siap diinstall.

## Pakai keystore sendiri (opsional)

Supaya APK bisa di-update tanpa uninstall dan siap dipublikasikan ke Play Store,
tambahkan repository secrets berikut di Settings > Secrets and variables > Actions:

- KEYSTORE_BASE64   (isi file keystore .jks yang sudah di-encode base64)
- KEYSTORE_PASSWORD
- KEY_ALIAS
- KEY_PASSWORD

Kalau secrets ini kosong, workflow otomatis membuat debug keystore supaya
APK tetap valid dan bisa diinstall untuk testing.
