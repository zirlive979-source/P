package com.webtoapk.apk;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private SwipeRefreshLayout swipeRefresh;
    private static final String LOAD_URL = "file:///android_asset/www/index.html";
    private static final boolean EXIT_CONFIRM = true;
    private static final boolean OPEN_EXTERNAL = false;
    private static final boolean PULL_REFRESH = true;
    private static final boolean FULLSCREEN = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (FULLSCREEN) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        swipeRefresh = findViewById(R.id.swipeRefresh);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowFileAccess(true);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (OPEN_EXTERNAL && !url.startsWith("file:///android_asset") && !url.contains(getBaseHost())) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                swipeRefresh.setRefreshing(false);
            }
        });

        swipeRefresh.setEnabled(PULL_REFRESH);
        swipeRefresh.setOnRefreshListener(() -> webView.reload());

        requestRuntimePermissions();

        webView.loadUrl(LOAD_URL);
    }

    private String getBaseHost() {
        try {
            String h = Uri.parse(LOAD_URL).getHost();
            return h == null ? "" : h;
        } catch (Exception e) {
            return "";
        }
    }

    private void requestRuntimePermissions() {
        java.util.List<String> perms = new java.util.ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) perms.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!perms.isEmpty() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this, perms.toArray(new String[0]), 100);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else if (EXIT_CONFIRM) {
            new AlertDialog.Builder(this)
                .setMessage("Keluar aplikasi?")
                .setPositiveButton("Ya", (d, w) -> finish())
                .setNegativeButton("Tidak", null)
                .show();
        } else {
            super.onBackPressed();
        }
    }
}
