package com.example.mylaundryadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button login;
    final String username="Admin";
    final String pass="54321";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.loginbtn);

        login.setOnClickListener(v -> {
           loginFn();
        });


    }

    private void loginFn() {

        if(email.getText().toString().equals(username) && password.getText().toString().equals(pass)){
        startActivity(new Intent(MainActivity.this,adminPage.class));
        }else{
            Toast.makeText(this, "Wrong Username or password", Toast.LENGTH_SHORT).show();
        }
    }
}