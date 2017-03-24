package com.userdetails.forms;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.model.Person;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;


public class UserDetailShowActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST_CODE = 100;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Person person = intent.getParcelableExtra("Person");

        String name = person.getName();
        String age = person.getAge();
        String phoneNumber = person.getPhoneNumber();
        String email = person.getEmail();

        setAllViews(name, age, phoneNumber, email);
    }

    @OnClick(R.id.image)
    public void onImageClick() {
        if (!hadTakenPhoto) {
            showAlertDialogAndLaunchCamera(R.string.note, R.string.camera_message);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
            toast.setView(getLayoutInflater().inflate(R.layout.layout_custom_toast, (ViewGroup) findViewById(R.id.custom_layout)));
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();
        }
    }

    private void showAlertDialogAndLaunchCamera(int title, int message) {
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
            if (photo != null) {
                image.setImageBitmap(photo);
                imageText.setVisibility(GONE);
                hadTakenPhoto = true;
            }
        }
    }

    private void setAllViews(String name, String age, String phoneNumber, String email) {
        this.name.setText(name);
        this.age.setText(age);
        this.phoneNumber.setText(phoneNumber);
        this.email.setText(email);
    }
}
