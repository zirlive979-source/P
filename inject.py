import os,re,pathlib
root=pathlib.Path("android-template")
app=os.environ.get("APP_NAME","WebToApkVD App")
pkg=os.environ.get("PACKAGE_NAME","com.webtoapkvd.app")
ver=os.environ.get("VERSION_NAME","1.0.0")
url=os.environ.get("URL","https://example.com")
if not re.fullmatch(r"[A-Za-z][A-Za-z0-9_.]{2,79}",pkg) or "." not in pkg:
    raise SystemExit("Invalid package name")
p=root/"app/build.gradle"; s=p.read_text()
s=re.sub(r"namespace\s+['"][^'"]+['"]",f"namespace '{pkg}'",s)
s=re.sub(r"applicationId\s+['"][^'"]+['"]",f"applicationId '{pkg}'",s)
s=re.sub(r"versionName\s+['"][^'"]+['"]",f"versionName '{ver}'",s); p.write_text(s)
p=root/"app/src/main/AndroidManifest.xml"; p.write_text(p.read_text().replace('android:label="WebToApkVD App"',f'android:label="{app}"'))
p=root/"app/src/main/java/com/webtoapkvd/app/MainActivity.kt"; p.write_text(p.read_text().replace("https://example.com",url))