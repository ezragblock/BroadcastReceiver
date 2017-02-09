package com.example.yedid.finalapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yedid.finalapp.R;

public class BusinessWebView extends AppCompatActivity {

    private WebView webView;

    /**
     * on the creation of the webview activity,
     * it's in charge of setting up all the things including the webview itself with the website from the business
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_web_view);
        setTitle("Trip website");

        webView = (WebView) findViewById(R.id.businessSite);
        webView.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        webView.setWebViewClient(new Callback());
        webView.loadUrl(address);
    }

    /**
     * Unused method
     */
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
