package com.userdetails.forms.presenter;

import android.graphics.Bitmap;

import com.userdetails.forms.R;
import com.userdetails.forms.UserDetailView;

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
        if(photo != null) {
            view.setImageView(photo);
            view.imageTextVisibility(GONE);
        }
    }
}
