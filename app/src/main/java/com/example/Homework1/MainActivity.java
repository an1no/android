package com.example.Homework1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Homework1.data.UsersStorage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmail = findViewById(R.id.email);
                String email = String.valueOf(mEmail.getText());
                mPassword = findViewById(R.id.password);
                String password = String.valueOf(mPassword.getText());
                LogIn(email, password);
            }
        });
    }

    public void LogIn(String email, String pass) {
        UsersStorage storage = new UsersStorage();
        Object usersAsObject = storage
                .getObject(this, "users_storage", Users.class);
        Users users;
        if (usersAsObject != null) {
            users = (Users) usersAsObject;
            ArrayList<User> users1 = users.getUsers();
            for (User user : users1) {
                if (user.getEmail().equals(email) && user.getPassword().equals(pass)) {
                    Intent intent = new Intent(this, ShowMoviesActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        } else {
            Toast toast = Toast.makeText(this, "Incorrect email or password.", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}
