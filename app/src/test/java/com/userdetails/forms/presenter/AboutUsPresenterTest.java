package com.userdetails.forms.presenter;

import com.userdetails.forms.view.about_us.AboutUsView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static android.view.View.VISIBLE;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AboutUsPresenterTest {

    @Mock
    AboutUsView view;

    private AboutUsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new AboutUsPresenter(view);
    }

    @Test
    public void shouldShowWebViewOnAboutUsActivityWhenInternetIsAvailable() throws Exception {
        boolean isInternetAvailable = true;

        presenter.showAboutUsScreen(isInternetAvailable);

        verify(view).setWebViewVisibility(VISIBLE);
    }

    @Test
    public void shouldShowNoInternetScreenOnAboutUsActivityWhenInternetIsNotAvailable() throws Exception {
        boolean isInternetAvailable = false;

        presenter.showAboutUsScreen(isInternetAvailable);

        verify(view).setNoInternetScreenVisibility(VISIBLE);

    }
}