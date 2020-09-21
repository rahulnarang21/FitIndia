package com.fitness365.fitindia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.helper.AppConfig;
import com.fitness365.fitindia.helper.ConnectionDetector;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    TextView toolbarTitle;
    ImageView backBtn;
    ProgressBar progressBar;
    WebView webView;
    ConnectionDetector connectionDetector;
    //private ImageView noInternetImage;
    private LinearLayout noInternetLayout;
    private TextView retryBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        toolbarTitle = findViewById(R.id.toolbar_title);
        backBtn = findViewById(R.id.back_btn);
        webView = findViewById(R.id.webview);
        progressBar = findViewById(R.id.progress_bar);
        noInternetLayout = findViewById(R.id.no_internet_layout);
        retryBtn = findViewById(R.id.retry_btn);
        webView.getSettings().setJavaScriptEnabled(true);
        connectionDetector = new ConnectionDetector(this);
        hideShowViews();

        retryBtn.setOnClickListener(this);

        intent = this.getIntent();
        toolbarTitle.setText(intent.getStringExtra(AppConfig.INTENT_STRING_EXTRA));
        Typeface font_semi_bold = Typeface.createFromAsset(getAssets(),
                "fonts/Barlow-BlackItalic.otf");
        toolbarTitle.setTypeface(font_semi_bold);

        loadUrl();

        backBtn.setOnClickListener(this);
    }

    private void loadUrl(){
        webView.setWebViewClient(new Callback());
        webView.loadUrl(intent.getStringExtra(AppConfig.INTENT_URL_EXTRA));
    }

    private void hideShowViews(){
        if (connectionDetector.isConnectingToInternet()){
            webView.setVisibility(View.VISIBLE);
            noInternetLayout.setVisibility(View.GONE);
        }
        else {
            webView.setVisibility(View.GONE);
            noInternetLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_btn)
            super.onBackPressed();
        else if (view.getId() == R.id.retry_btn) {
            if (connectionDetector.isConnectingToInternet()) {
                progressBar.setVisibility(View.VISIBLE);
                loadUrl();
            }
        }
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            webView.loadUrl(url);
//            return true;
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
            hideShowViews();

        }
    }
}
