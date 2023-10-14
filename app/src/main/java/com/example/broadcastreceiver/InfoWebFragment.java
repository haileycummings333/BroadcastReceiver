package com.example.broadcastreceiver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;

public class InfoWebFragment extends Fragment {
    private WebView webView;

    public InfoWebFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_web, container, false);

        // Initialize the WebView
        webView = v.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript

        // Check if a URL was passed as an argument
        Bundle args = getArguments();
        if (args != null && args.containsKey("url")) {
            String url = args.getString("url");
            loadWebPage(url);
        } else {
            // If no URL is provided, load a default URL
            loadWebPage("https://seekingalpha.com");
        }

        return v;
    }

    private void loadWebPage(String url) {
        // Load the web page in the WebView
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
