package com.userdetails.forms.presenter;

import com.userdetails.forms.view.about_us.AboutUsView;

import static android.view.View.VISIBLE;

public class AboutUsPresenter {

    private AboutUsView view;

    public AboutUsPresenter(AboutUsView view) {
        this.view = view;
    }

    public void showAboutUsScreen(boolean isInternetAvailable) {
        if (isInternetAvailable) {
            view.setWebViewVisibility(VISIBLE);
        }else {
            view.setNoInternetScreenVisibility(VISIBLE);
        }
    }
}
