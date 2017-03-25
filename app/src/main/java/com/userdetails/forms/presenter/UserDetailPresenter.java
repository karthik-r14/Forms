package com.userdetails.forms.presenter;

import com.userdetails.forms.R;
import com.userdetails.forms.UserDetailView;

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
}
