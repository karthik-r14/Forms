package com.userdetails.forms.view.rate_us;

public interface RateUsView {
    void setButtonLayoutVisibility(int visibility);

    void setColorOfRatingBar(int color);

    void setTapStarsVisibility(int visibility);

    void setButtonText(int buttonText);

    void notifyAverageRatingToUser(double averageRating);
}
