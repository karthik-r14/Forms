package com.userdetails.forms.view.about_us;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.userdetails.forms.BuildConfig;
import com.userdetails.forms.R;
import com.userdetails.forms.presenter.AboutUsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity implements AboutUsView {

    public static final String URL = "https://github.com/karthik-r14";

    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.no_internet_layout)
    LinearLayout noInternetLayout;

    private AboutUsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        presenter = new AboutUsPresenter(this);

        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(URL);

        presenter.showAboutUsScreen(isConnectivityAvailable());
    }

    @Override
    public void setWebViewVisibility(int visibility) {
        webView.setVisibility(visibility);
    }

    @Override
    public void setNoInternetScreenVisibility(int visibility) {
        noInternetLayout.setVisibility(visibility);
    }

    private boolean isConnectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
