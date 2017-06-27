package com.userdetails.forms.view.show_user_details;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.R;
import com.userdetails.forms.model.Person;
import com.userdetails.forms.presenter.UserDetailPresenter;
import com.userdetails.forms.view.FAQs.FAQsActivity;
import com.userdetails.forms.view.about_us.AboutUsActivity;
import com.userdetails.forms.view.feedback.FeedbackActivity;
import com.userdetails.forms.view.form.FormActivity;
import com.userdetails.forms.view.more.MoreActivity;
import com.userdetails.forms.view.rate_us.RateUsDialogFragment;
import com.userdetails.forms.view.scan_barcode.ScanBarcodeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;


public class UserDetailShowActivity extends AppCompatActivity implements UserDetailView {
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final String ABOUT_US = "AboutUs";
    public static final String RATE_US = "RateUs";
    public static final String FEEDBACK = "Feedback";
    public static final String FAQS = "FAQs";
    public static final String PERSON = "Person";
    public static final String SHARE = "Share";
    public static final String MORE = "More";
    public static final String SCAN_BARCODE = "scan_barcode";
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.image_text)
    TextView imageText;

    private boolean hadTakenPhoto;
    private UserDetailPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_detail);
        ButterKnife.bind(this);
        presenter = new UserDetailPresenter(this);

        Intent intent = getIntent();
        Person person = intent.getParcelableExtra(PERSON);

        String name = person.getName();
        String age = person.getAge();
        String phoneNumber = person.getPhoneNumber();
        String email = person.getEmail();

        setAllViews(name, age, phoneNumber, email);
    }

    @OnClick(R.id.image)
    public void onImageClick() {
        presenter.onImageClick(hadTakenPhoto);
    }

    @Override
    public void showCustomToast() {
        Toast toast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        toast.setView(getLayoutInflater().inflate(R.layout.layout_custom_toast, (ViewGroup) findViewById(R.id.custom_layout)));
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    @Override
    public void imageTextVisibility(int visibility) {
        imageText.setVisibility(GONE);
    }

    @Override
    public void setImageView(Bitmap photo) {
        image.setImageBitmap(photo);
    }

    @Override
    public void startAboutUsActivity() {
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRateUsDialog() {
        RateUsDialogFragment rateUsDialogFragment = RateUsDialogFragment.newInstance();
        rateUsDialogFragment.show(getFragmentManager(), RateUsDialogFragment.TAG);
    }

    @Override
    public void startFeedbackActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, FeedbackActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFAQSActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, FAQsActivity.class);
        startActivity(intent);
    }

    @Override
    public void shareByMessagingApps() {
        SharedPreferences preferences = getSharedPreferences(FormActivity.USER_DETAILS, Context.MODE_PRIVATE);
        String name = preferences.getString(FormActivity.NAME, getString(R.string.sample_name));
        String age = preferences.getString(FormActivity.AGE, getString(R.string.sample_age));
        String email = preferences.getString(FormActivity.EMAIL, getString(R.string.sample_email));
        String phone = preferences.getString(FormActivity.PHONE, getString(R.string.sample_phone_number));

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_details_message, name, age, email, phone));
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.share_the_details)));
    }

    @Override
    public void startMoreActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, MoreActivity.class);
        startActivity(intent);
    }

    @Override
    public void startScanBarcodeActivity() {
        Intent intent = new Intent(UserDetailShowActivity.this, ScanBarcodeActivity.class);
        startActivity(intent);
    }

    @Override
    public void showAlertDialogAndLaunchCamera(int title, int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            hadTakenPhoto = true;
            presenter.setImageLayoutBasedOnPhoto(photo);
        }
    }

    private void setAllViews(String name, String age, String phoneNumber, String email) {
        this.name.setText(name);
        this.age.setText(age);
        this.phoneNumber.setText(phoneNumber);
        this.email.setText(email);
    }

    private boolean isConnectivityAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean internetAvailable = isConnectivityAvailable();
        if (!internetAvailable) {
            if (item.getItemId() != R.id.share) {
                Toast.makeText(getApplicationContext(), R.string.no_internet, Toast.LENGTH_SHORT).show();
            }
        }

        switch (item.getItemId()) {
            case R.id.about_us:
                if (internetAvailable)
                    presenter.onMenuItemClick(ABOUT_US);
                return true;
            case R.id.rate_us:
                if (internetAvailable)
                    presenter.onMenuItemClick(RATE_US);
                return true;
            case R.id.feedback:
                if (internetAvailable)
                    presenter.onMenuItemClick(FEEDBACK);
                return true;
            case R.id.faqs:
                if (internetAvailable)
                    presenter.onMenuItemClick(FAQS);
                return true;
            case R.id.share:
                presenter.onMenuItemClick(SHARE);
                return true;
            case R.id.more:
                if (internetAvailable)
                    presenter.onMenuItemClick(MORE);
                return true;
            case R.id.scan_barcode:
                presenter.onMenuItemClick(SCAN_BARCODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
