package com.userdetails.forms.presenter;

import com.userdetails.forms.R;
import com.userdetails.forms.view.rate_us.RateUsView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class RateUsDialogPresenter {
    private static final float THRESHOLD = 3.0f;
    private RateUsView view;

    public RateUsDialogPresenter(RateUsView view) {
        this.view = view;
    }

    public void onRatingBarTouch(float rating) {
        if (rating <= THRESHOLD) {
            if (rating == 0.0f) {
                view.setButtonLayoutVisibility(GONE);
                view.setTapStarsVisibility(VISIBLE);
            } else {
                view.setButtonLayoutVisibility(VISIBLE);
                view.setColorOfRatingBar(R.color.red);
                view.setTapStarsVisibility(GONE);
            }
        } else {
            view.setButtonLayoutVisibility(VISIBLE);
            view.setColorOfRatingBar(R.color.turquoise);
            view.setTapStarsVisibility(GONE);
        }
    }
}
