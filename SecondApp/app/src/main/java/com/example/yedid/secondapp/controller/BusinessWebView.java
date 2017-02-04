package com.example.yedid.secondapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yedid.secondapp.R;

public class BusinessWebView extends AppCompatActivity {

    private WebView webView;

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
        webView.loadUrl("https://" + address);
    }

    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}
