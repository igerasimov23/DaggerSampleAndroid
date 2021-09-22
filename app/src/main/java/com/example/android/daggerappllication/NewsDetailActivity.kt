package com.example.android.daggerappllication

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.ContentLoadingProgressBar
import com.google.android.material.snackbar.Snackbar

class NewsDetailActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("NewsDetailActivity", "onCreate: ")
        Log.i("TAG", "onCreate: $contentUrl")


        setupNavBar()
        setContentView(R.layout.activity_webview)

        val webView :WebView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object
            : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (!isFinishing && !isDestroyed) {
                    val bar = findViewById<ContentLoadingProgressBar>(R.id.progressbar)
                    bar.show()
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (!isFinishing && !isDestroyed) {
                    val bar = findViewById<ContentLoadingProgressBar>(R.id.progressbar)
                    bar.hide()
                }
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                if (request?.isForMainFrame == true) {
                    view?.run {
                        stopLoading()
                        loadUrl("about:blank")
                        val snackbar = Snackbar.make(
                            this,
                            "Sorry, we couldn\\'t load this page.",
                            Snackbar.LENGTH_LONG
                        )
                        snackbar.view.background = null
                        snackbar.show()
                    }
                }
            }



        }

        webView.loadUrl(contentUrl)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "News"
    }


    companion object {
        var contentUrl: String? = ""

        fun newIntent(context: Context, url: String?) : Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra("contentUrl", url)
            contentUrl = url

            return intent
        }
    }
}
