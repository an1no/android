package com.example.Homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Homework1.data.UsersStorage;

public class SignUp extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private EditText mUserName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_signup);
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserName = findViewById(R.id.username);
                String userName = String.valueOf(mUserName.getText());
                mEmail = findViewById(R.id.email);
                String email = String.valueOf(mEmail.getText());
                mPassword = findViewById(R.id.password);
                String password = String.valueOf(mPassword.getText());
                AddUserToStorage(userName, email, password);
            }
        });
    }

    public void AddUserToStorage(String userName, String email, String pass) {
        UsersStorage storage = new UsersStorage();
        Object usersAsObject = storage
                .getObject(this, "users_storage", Users.class);
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(pass);
        Users users;
        if (usersAsObject != null) {
            users = (Users) usersAsObject;
        } else {
            users = new Users();
        }
        users.addUsers(user);
        storage.add(SignUp.this, "users_storage", users);
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SignIn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
