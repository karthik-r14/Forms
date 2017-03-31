package com.userdetails.forms.view.about_us;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.userdetails.forms.R;

import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {

    public static final String URL = "https://github.com/karthik-r14";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        WebView webView = (WebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);
    }
}
