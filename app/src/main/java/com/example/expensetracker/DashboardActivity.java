package com.example.expensetracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity {

    MaterialToolbar topAppBar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        topAppBar = findViewById(R.id.topAppBar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        fab=findViewById(R.id.floatingActionButton);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id= item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);
                switch(id) {
                    case R.id.item_settings: {
                        //Write Code here to navigate to settings page
                        break;
                    }
                    case R.id.item_help_feedback: {
                        //Write code here for help and feedback
                        break;
                    }
                    case R.id.item_logout:{
                        //Write code here for logout
                        break;
                    }
                    default:
                        return true;
                }
                return true;
            }
        });

        fab.setOnClickListener((view -> {
            startActivity(new Intent(DashboardActivity.this,NextActivity.class));
        }));
    }
}