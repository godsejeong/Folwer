package com.hwd.flower.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.hwd.flower.R
import kotlinx.android.synthetic.main.activity_ordering.*

class OrderingActivity : AppCompatActivity() {
    var link = "http://xn--ig2brfw80b5lbd59a.kr/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordering)

        webView.webViewClient = WebViewClient()
        val setting = webView.settings
        setting.javaScriptEnabled = true
        webView.loadUrl(link)

    }
}
