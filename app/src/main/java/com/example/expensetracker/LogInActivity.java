package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {
    
    TextInputEditText email_tf;
    TextInputEditText password_tf;
    Button login;
    int MIN_PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//adding back button in action bar
        email_tf = findViewById(R.id.email_tf);
        password_tf = findViewById(R.id.password_et);
        login = findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_tf.getText().toString();
                String password = password_tf.getText().toString();
                SharedPreferences preferences = getSharedPreferences("default",MODE_PRIVATE);
                if(preferences.getString("email",null).equals(email) && preferences.getString("password",null).equals(password)) {
                    Intent displayScreen = new Intent(LogInActivity.this, DashboardActivity.class);
                    startActivity(displayScreen);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Email or Password Incorrect",Toast.LENGTH_SHORT).show();
                }
            }
            });
    }

    //function to move back to parent activity declared in manifest file
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}