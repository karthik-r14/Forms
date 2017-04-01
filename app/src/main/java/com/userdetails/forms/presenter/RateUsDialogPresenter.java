package com.userdetails.forms.presenter;

import com.userdetails.forms.view.rate_us.RateUsView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RateUsDialogPresenter {
    private RateUsView view;

    public RateUsDialogPresenter(RateUsView view) {
        this.view = view;
    }

    public void onRatingBarTouch(float rating) {
        if (rating == 0.0f) {
            view.setButtonLayoutVisibility(GONE);
        } else {
            view.setButtonLayoutVisibility(VISIBLE);

        }
    }
}
