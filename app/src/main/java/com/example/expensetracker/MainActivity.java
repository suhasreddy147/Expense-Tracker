package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //button declaration
    Button log_in;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide(); //Hide the action bar

        //button assignment to component in XML layout file
        log_in = findViewById(R.id.login_btn);
        sign_up = findViewById(R.id.signup_btn);

        //adding function to login button
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this,LogInActivity.class);// declaring intent object
                MainActivity.this.startActivity(intentLogin);//starting Login Activity on click
            }
        });
        //adding function to sign up button
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSignUp = new Intent(MainActivity.this,SignUpActivity.class);// declaring intent object
                MainActivity.this.startActivity(intentSignUp);//starting Sign Up Activity on click
            }
        });
    }
}