package com.fitness365.fitindia.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.app.Fitness365App;
import com.fitness365.fitindia.helper.AppConfig;
import com.fitness365.fitindia.helper.ConnectionDetector;
import com.fitness365.fitindia.helper.ConnectivityReceiverListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private WebView webView;
    private ProgressBar progressBar;
    ConnectionDetector connectionDetector;
    //private ImageView noInternetImage;
    private LinearLayout noInternetLayout;
    private TextView retryBtn;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_layout, container, false);
        //TextView toolbarTitle = view.findViewById(R.id.toolbar_title);
        webView = view.findViewById(R.id.webview);
        progressBar = view.findViewById(R.id.progress_bar);
        //noInternetImage = view.findViewById(R.id.no_internet_image);
        noInternetLayout = view.findViewById(R.id.no_internet_layout);
        retryBtn = view.findViewById(R.id.retry_btn);
        webView.getSettings().setJavaScriptEnabled(true);
        connectionDetector = new ConnectionDetector(getContext());
        hideShowViews();

        retryBtn.setOnClickListener(this);
        loadUrl();

//        try {
//            toolbarTitle.setText(bundle.getString(AppConfig.INTENT_STRING_EXTRA));
//            webView.loadUrl(bundle.getString(AppConfig.INTENT_URL_EXTRA));
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }


        return view;
    }

    private void loadUrl(){
        webView.setWebViewClient(new Callback());

        Bundle bundle = getArguments();
        if (bundle!=null) {
            if (Objects.equals(bundle.getString(AppConfig.INTENT_STRING_EXTRA), getString(R.string.get_fit)))
                webView.loadUrl(bundle.getString(AppConfig.INTENT_URL_EXTRA)+bundle.getString(AppConfig.USER_ID));
            else
                webView.loadUrl(bundle.getString(AppConfig.INTENT_URL_EXTRA));
        }
        else
            Toast.makeText(getActivity(), "Something went wrong! Please load again!", Toast.LENGTH_SHORT).show();

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
        if (view.getId() == R.id.retry_btn) {
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
