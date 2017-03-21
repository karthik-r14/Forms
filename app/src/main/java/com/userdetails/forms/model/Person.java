package com.userdetails.forms.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Person implements Parcelable {
    private String name;
    private String age;
    private String phoneNumber;
    private String address;

    public Person(String name, String age, String phoneNumber, String address) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readString();
        phoneNumber = in.readString();
        address = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(phoneNumber);
        dest.writeString(address);
    }
}
