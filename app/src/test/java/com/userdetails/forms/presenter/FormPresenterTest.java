package com.userdetails.forms.presenter;

import com.userdetails.forms.FormView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FormPresenterTest {
    @Mock
    FormView view;

    FormPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new FormPresenter(view);
    }

    @Test
    public void shouldShowNameErrorWhenNameIsEmpty() throws Exception {
        String name = "";
        String age = "23";
        String phoneNumber = "9880443410";
        String address = "Hebbal";

        presenter.validate(name, age, phoneNumber, address);

        verify(view).nameErrorVisibility(GONE);
        verify(view).ageErrorVisibility(GONE);
        verify(view).phoneErrorVisibility(GONE);
        verify(view).addressErrorVisibility(GONE);

        verify(view).nameErrorVisibility(VISIBLE);
    }

    @Test
    public void shouldShowAgeErrorWhenAgeIsEmpty() throws Exception {
        String name = "Karthik";
        String age = "";
        String phoneNumber = "9880443410";
        String address = "Hebbal";

        presenter.validate(name, age, phoneNumber, address);

        verify(view).nameErrorVisibility(GONE);
        verify(view).ageErrorVisibility(GONE);
        verify(view).phoneErrorVisibility(GONE);
        verify(view).addressErrorVisibility(GONE);

        verify(view).ageErrorVisibility(VISIBLE);
    }

    @Test
    public void shouldShowPhoneErrorWhenPhoneNumberIsEmpty() throws Exception {
        String name = "Karthik";
        String age = "23";
        String phoneNumber = "";
        String address = "Hebbal";

        presenter.validate(name, age, phoneNumber, address);

        verify(view).nameErrorVisibility(GONE);
        verify(view).ageErrorVisibility(GONE);
        verify(view).phoneErrorVisibility(GONE);
        verify(view).addressErrorVisibility(GONE);

        verify(view).phoneErrorVisibility(VISIBLE);
    }

    @Test
    public void shouldShowAddressErrorWhenAddressIsEmpty() throws Exception {
        String name = "Karthik";
        String age = "23";
        String phoneNumber = "9880443410";
        String address = "";

        presenter.validate(name, age, phoneNumber, address);

        verify(view).nameErrorVisibility(GONE);
        verify(view).ageErrorVisibility(GONE);
        verify(view).phoneErrorVisibility(GONE);
        verify(view).addressErrorVisibility(GONE);

        verify(view).addressErrorVisibility(VISIBLE);
    }
}