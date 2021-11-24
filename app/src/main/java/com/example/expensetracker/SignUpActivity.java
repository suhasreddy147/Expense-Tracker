package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText email_tf;
    TextInputEditText name_tf;
    TextInputEditText password_tf;
    TextInputLayout email_layout;
    TextInputLayout name_layout;
    TextInputLayout password_layout;
    Button sign_up;

    int MIN_PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//adding back button in action bar

        sign_up = findViewById(R.id.signup_btn);

        email_tf = findViewById(R.id.email_et);
        name_tf = findViewById(R.id.name_et);
        password_tf = findViewById(R.id.password_et);

        email_layout = findViewById(R.id.email_field);
        password_layout = findViewById(R.id.password_tf);
        name_layout = findViewById(R.id.name_tf);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("default",MODE_PRIVATE);

                if (validateInput()) {
                    String name = name_tf.getText().toString();
                    String email = email_tf.getText().toString();
                    String password = password_tf.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("email",email);
                    editor.commit();
                    editor.putString("name", name);
                    editor.commit();
                    editor.putString("password", password);
                    editor.commit();

                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    boolean validateInput() {

        if (name_tf.getText().toString().equals("")) {
            return false;
        }
        if (email_tf.getText().toString().equals("")) {
            return false;
        }
        if (password_tf.getText().toString().equals("")) {
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(email_tf.getText().toString())) {
            email_layout.setError("Invalid Format");
            return false;
        }
        // checking minimum password Length
        if (password_tf.getText().length() < MIN_PASSWORD_LENGTH) {
            password_layout.setError("Password should be of minimum 8 characters");
            return false;
        }
        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}