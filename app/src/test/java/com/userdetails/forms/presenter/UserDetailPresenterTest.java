package com.userdetails.forms.presenter;

import android.graphics.Bitmap;

import com.userdetails.forms.R;
import com.userdetails.forms.UserDetailView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static android.view.View.GONE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDetailPresenterTest {
    @Mock
    private UserDetailView view;

    private UserDetailPresenter presenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        presenter = new UserDetailPresenter(view);
    }

    @Test
    public void shouldShowAlertDialogAndLaunchCameraIfPhotoHasNotBeenClicked() throws Exception {
        boolean hadTakenPhoto = false;

        presenter.onImageClick(hadTakenPhoto);

        verify(view, never()).showCustomToast();
        verify(view).showAlertDialogAndLaunchCamera(R.string.note, R.string.camera_message);
    }

    @Test
    public void shouldShowCustomToastIfPhotoHasBeenClicked() throws Exception {
        boolean hadTakenPhoto = true;

        presenter.onImageClick(hadTakenPhoto);

        verify(view, never()).showAlertDialogAndLaunchCamera(R.string.note, R.string.camera_message);
        verify(view).showCustomToast();
    }

    @Test
    public void shouldSetImageViewAndImageTextVisibilityIfPhotoIsClicked() throws Exception {
        Bitmap photo = mock(Bitmap.class);

        presenter.setImageLayoutBasedOnPhoto(photo);

        verify(view).imageTextVisibility(GONE);
        verify(view).setImageView(photo);
    }
}