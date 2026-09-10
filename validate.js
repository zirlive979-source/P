const fs=require("fs");
const required=["index.html","css/style.css","js/app.js",".github/workflows/build-apk.yml","android-template/app/src/main/AndroidManifest.xml"];
const missing=required.filter(x=>!fs.existsSync(x));
if(missing.length){console.error("Missing:",missing);process.exit(1)}
console.log("WebToApkVD V1 structure OK");