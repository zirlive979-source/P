package com.webtoapkvd.app

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    private lateinit var web: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        web = WebView(this)
        web.webViewClient = WebViewClient()
        web.settings.javaScriptEnabled = true
        web.settings.domStorageEnabled = true
        setContentView(web)
        web.loadUrl("https://example.com")
    }
    @Deprecated("Deprecated by Android API")
    override fun onBackPressed() {
        if (web.canGoBack()) web.goBack() else super.onBackPressed()
    }
}