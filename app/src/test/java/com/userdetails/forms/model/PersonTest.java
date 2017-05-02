package com.userdetails.forms.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {

    private Person person;

    @Before
    public void setUp() throws Exception {
        person = new Person("Karthik", "23", "9880443410", "karthikr1493@gmail.com");
    }

    @Test
    public void shouldGetName() throws Exception {
        assertEquals("Karthik", person.getName());
    }

    @Test
    public void shouldGetAge() throws Exception {
        assertEquals("23", person.getAge());
    }

    @Test
    public void getPhoneNumber() throws Exception {
        assertEquals("9880443410", person.getPhoneNumber());
    }

    @Test
    public void getEmail() throws Exception {
        assertEquals("karthikr1493@gmail.com", person.getEmail());
    }
}