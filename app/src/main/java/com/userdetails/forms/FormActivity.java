package com.userdetails.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.presenter.FormPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormActivity extends AppCompatActivity implements FormView {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.name_error)
    TextView nameError;
    @BindView(R.id.age_error)
    TextView ageError;
    @BindView(R.id.phone_error)
    TextView phoneError;
    @BindView(R.id.address_error)
    TextView addressError;

    private FormPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ButterKnife.bind(this);
        presenter = new FormPresenter(this);
    }

    @OnClick(R.id.submit_button)
    void submitButtonClick() {
        String name = this.name.getText().toString();
        String age = this.age.getText().toString();
        String phoneNumber = this.phoneNumber.getText().toString();
        String address = this.address.getText().toString();

        presenter.validate(name, age, phoneNumber, address);

        if (!presenter.hasErrors()) {
            Toast.makeText(getApplicationContext(), R.string.submit_message,
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), UserDetailShowActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("age", age);
            intent.putExtra("phoneNumber", phoneNumber);
            intent.putExtra("address", address);

            startActivity(intent);
        }
    }

    @Override
    public void nameErrorVisibility(int visibility) {
        nameError.setVisibility(visibility);
        nameError.requestFocus();
    }

    @Override
    public void ageErrorVisibility(int visibility) {
        ageError.setVisibility(visibility);
        ageError.requestFocus();
    }

    @Override
    public void phoneErrorVisibility(int visibility) {
        phoneError.setVisibility(visibility);
        phoneError.requestFocus();
    }

    @Override
    public void addressErrorVisibility(int visibility) {
        addressError.setVisibility(visibility);
        addressError.requestFocus();
    }
}