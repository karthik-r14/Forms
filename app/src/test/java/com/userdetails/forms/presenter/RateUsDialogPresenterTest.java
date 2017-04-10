package com.userdetails.forms.presenter;


import android.view.View;

import com.userdetails.forms.R;
import com.userdetails.forms.view.rate_us.RateUsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static org.mockito.Matchers.floatThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class RateUsDialogPresenterTest {
    @Mock
    private RateUsView view;

    private RateUsDialogPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new RateUsDialogPresenter(view);
    }

    @Test
    public void shouldShowButtonsNotNowAndSubmitIfRatingIsGreaterThanZero() throws Exception {
        float rating = 4.0f;

        presenter.onRatingBarTouch(rating);

        verify(view).setButtonLayoutVisibility(VISIBLE);
    }

    @Test
    public void shouldHideButtonsIfRatingIsZero() throws Exception {
        float rating = 0.0f;

        presenter.onRatingBarTouch(rating);

        verify(view).setButtonLayoutVisibility(GONE);
        verify(view, never()).setButtonLayoutVisibility(VISIBLE);
    }

    @Test
    public void shouldShowButtonsAndSetColorOfRatingBarAsRedIfRatingIsLessThanOrEqualToThreshold() throws Exception {
        float rating = 3.0f;

        presenter.onRatingBarTouch(rating);

        verify(view).setColorOfRatingBar(R.color.red);
        verify(view).setButtonLayoutVisibility(VISIBLE);
    }

    @Test
    public void shouldShowButtonsAndSetColorOfRatingBarAsTurquoiseIfRatingIsGreaterThanThreshold() throws Exception {
        float rating = 4.0f;

        presenter.onRatingBarTouch(rating);

        verify(view).setColorOfRatingBar(R.color.turquoise);
        verify(view).setButtonLayoutVisibility(VISIBLE);
    }
}