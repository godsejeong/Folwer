package com.hod.flower.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.hod.flower.R
import kotlinx.android.synthetic.main.activity_ordering.*
import android.webkit.WebView


class OrderingActivity : AppCompatActivity() {
    var link :String? = null
    private var CurrentUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordering)

        link = intent.getStringExtra("link")

        webView.settings.javaScriptEnabled = true
        webView.settings.setJavaScriptEnabled(true);
        webView.settings.setLoadWithOverviewMode(true);
        webView.settings.setUseWideViewPort(true);
        webView.settings.setSupportZoom(true);
        webView.settings.setBuiltInZoomControls(false);
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN;
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE;
        webView.settings.domStorageEnabled = true;

        webView.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY;
        webView.isScrollbarFadingEnabled = true;
        webView.loadUrl(link)
        webView.webViewClient = WebviewClientClass() // 이걸 안해주면 새창이 뜸

    }

    private inner class WebviewClientClass : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {

            if (CurrentUrl != null && url != null && url == CurrentUrl) {
                webView.goBack()
            } else {
                view.loadUrl(url)
                CurrentUrl = url
            }
            return true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
