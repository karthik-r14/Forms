package com.userdetails.forms.presenter;

import android.graphics.Bitmap;

import com.userdetails.forms.R;
import com.userdetails.forms.view.show_user_details.UserDetailShowActivity;
import com.userdetails.forms.view.show_user_details.UserDetailView;

import static android.view.View.GONE;

public class UserDetailPresenter {
    private UserDetailView view;

    public UserDetailPresenter(UserDetailView view) {
        this.view = view;
    }

    public void onImageClick(boolean hadTakenPhoto) {
        if (!hadTakenPhoto) {
            view.showAlertDialogAndLaunchCamera(R.string.note, R.string.camera_message);
        } else {
            view.showCustomToast();
        }
    }

    public void setImageLayoutBasedOnPhoto(Bitmap photo) {
        if (photo != null) {
            view.setImageView(photo);
            view.imageTextVisibility(GONE);
        }
    }

    public void onMenuItemClick(String menuItemType) {
        switch (menuItemType) {
            case UserDetailShowActivity.ABOUT_US:
                view.startAboutUsActivity();
                break;

            case UserDetailShowActivity.RATE_US:
                view.showRateUsDialog();
                break;

            case UserDetailShowActivity.FEEDBACK:
                view.startFeedbackActivity();
                break;
        }
    }
}
