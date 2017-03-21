package com.userdetails.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.userdetails.forms.model.Person;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserDetailShowActivity extends AppCompatActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.address)
    TextView address;

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
        String address = person.getAddress();

        setAllViews(name, age, phoneNumber, address);
    }

    private void setAllViews(String name, String age, String phoneNumber, String address) {
        this.name.setText(name);
        this.age.setText(age);
        this.phoneNumber.setText(phoneNumber);
        this.address.setText(address);
    }
}
