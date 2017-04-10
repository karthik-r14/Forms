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
                setAllViews(R.color.red, R.string.feedback);
            }
        } else {
            setAllViews(R.color.turquoise, R.string.submit);
        }
    }

    private void setAllViews(int ratingBarColor, int buttonText) {
        view.setButtonLayoutVisibility(VISIBLE);
        view.setColorOfRatingBar(ratingBarColor);
        view.setTapStarsVisibility(GONE);
        view.setButtonText(buttonText);
    }
}
