package com.example.a4push;

import java.io.Serializable;





public class User implements Serializable {

    String email;
    String token;

    public User () {

    }

    public User(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
