package com.userdetails.forms.view.show_user_details;

import android.graphics.Bitmap;

public interface UserDetailView {
    void showAlertDialogAndLaunchCamera(int title, int message);

    void showCustomToast();

    void imageTextVisibility(int visibility);

    void setImageView(Bitmap photo);

    void startAboutUsActivity();

    void showRateUsDialog();

    void startFeedbackActivity();

    void startFAQSActivity();
}
