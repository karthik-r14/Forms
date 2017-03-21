package com.userdetails.forms.presenter;

import com.userdetails.forms.FormView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class FormPresenter {
    private FormView formView;
    private boolean errors;

    public FormPresenter(FormView formView) {
        this.formView = formView;
    }

    public void validate(String name, String age, String phoneNumber, String address) {
        hideAllErrors();

        if(name.trim().isEmpty()) {
            errors = true;
            formView.nameErrorVisibility(VISIBLE);
        }

        if(age.trim().isEmpty()) {
            errors = true;
            formView.ageErrorVisibility(VISIBLE);
        }

        if(phoneNumber.trim().isEmpty()) {
            errors = true;
            formView.phoneErrorVisibility(VISIBLE);
        }

        if(address.trim().isEmpty()) {
            errors = true;
            formView.addressErrorVisibility(VISIBLE);
        }
    }

    private void hideAllErrors() {
        errors = false;
        formView.nameErrorVisibility(GONE);
        formView.ageErrorVisibility(GONE);
        formView.phoneErrorVisibility(GONE);
        formView.addressErrorVisibility(GONE);
    }

    public boolean hasErrors() {
        return errors;
    }
}
